/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.physics;

import static java.lang.Math.round;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Spliterator;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.geom.Rectangle;
import yoshimaker.global.Entity;
import yoshimaker.global.characters.players.Player;

/**
 *
 * @author punpun
 */
public class Physics {
    private static World _world ;
    private Body body;
    private final BodyDef _definition;
    private final PolygonShape _hitbox;
    private final FixtureDef _fixtures;
    private boolean created, defined, fixtured, hitboxed;
    private Object data;
    private final static HashSet<Body> DESTROYED = new HashSet(), FORCED = new HashSet();
    private final static HashSet<Physics> CREATION = new HashSet();
    private int _group;

    /**
     * Contructeur
     * Note le constructeur ne fait qu'initaliser les données
     * Celles-ci sont à initaliser manuellement
     * Exemple de code :
     *
     * //Création du monde (requis avant toute instanciation !)
     * Physics.world(0, -10); //Vecteur de gravité
     *
     * //Initialisation
     * Physics p = new Physics();
     * p
     *      .define(BodyType.STATIC) //Type d'objet : STATIC/DYNAMIC/KINEMATIC
     *      .at(0, 0) //Position initiale
     *      .hitbox(1, 1) //Demi-dimensions de l'objet
     *      .fixtures(0.5f, 0.3.f, 0.5f) //Raccourcis pour les méthodes ci-dessous
     *      .create(); //Crée l'objet
     *
     * //Mis à jour du monde
     * Physics.render(delta);
     *
     * //Mise à jour de l'entité liée
     * entity.x = p.x();
     * entity.y = p.y();
     * entity.angle = p.angle();
     *
     * //Appliquer des forces à l'entité
     * p.impulse(+0.1f, 0); //Impulsion
     */
    public Physics() {
        //Initialisation
        created = false ;
        defined = false ;
        hitboxed = false ;
        fixtured = false ;
        data = null ;
        //JBox 2D initialisation
        _group = 1;
        _definition = new BodyDef();
        _hitbox = new PolygonShape();
        _fixtures = new FixtureDef();
        
    }
    
    public Physics data(Object d) {
        this.data = d;
        return this ;
    }
    
    public Object data() {
        if (!created) { return null; }
        return body.getUserData();
    }

    /**
     * Définition de l'objet
     * @return
     */
    public BodyDef definition() {
        return _definition;
    }

    /**
     * Définition du type d'objet
     * @param type
     * @return
     */
    public Physics definition(BodyType type, int group) {
        if (!created) {
            _definition.type = type;
            _group = group;
            defined = true;
        }
        return this;
    }

    /**
     * Raccourci de la méthode définition
     * @param type
     * @return
     */
    public Physics define(BodyType type) {
        return definition(type, _group);
    }
    
    public Physics define(BodyType type, int group) {
        return definition(type, group);
    }

    /**
     * Position du corps de l'objet
     * @return
     */
    public Vec2 at() {
        return definition().position ;
    }

    /**
     * Met à jour la position du corps de l'objet
     * @param x
     * @param y
     * @return
     */
    public Physics at(int x, int y) {
        if (defined) {
            definition().position.set(toMeters(x), toMeters(y));
        }
        return this;
    }


    /**
     * Position du corps de l'objet
     * @return
     */
    public Vec2 position() {
        return body.getPosition();
    }

    /**
     * Met à jour la position du corps de l'objet
     * @param x
     * @param y
     * @return
     */
    public Physics position(int x, int y) {
        if (created) {
            body.getPosition().set(toMeters(x), toMeters(y));
        }
        return this;
    }

    /**
     * Hitbox de l'objet
     * @return
     */
    public PolygonShape hitbox() {
        return _hitbox;
    }

    /**
     * Définit la hitbox de l'objet
     * hx et hy représente le centre de gravité de l'objet
     * ie : la moitié des dimensions si c'est un rectangle
     * @param hx
     * @param hy
     * @return
     */
    public Physics hitbox(int hx, int hy) {
        if (!created) {
            _hitbox.setAsBox(toMeters(hx), toMeters(hy));
            hitboxed = true;
        }
        return this;
    }

    /**
     * Propriétés de l'objet
     * @return
     */
    public FixtureDef fixtures() {
        return _fixtures;
    }

    /**
     * Définit les propriétés de l'objet
     * @param density - Densité en kg/m²
     * @param friction - Friction (0 à 1). O indique un corps glissant
     * @param restitution - Restitution (0 à 1). 1 indique un corps rebondissant
     * @return
     */
    public Physics fixtures(float density, float friction, float restitution) {
        if ((!created)&&(hitboxed)) {
            _fixtures.shape = hitbox();
            _fixtures.density = density;
            _fixtures.friction = friction;
            _fixtures.restitution = restitution;
            _fixtures.filter.categoryBits = _group;
            _fixtures.filter.maskBits = _group;
            fixtured = true ;
        }
        return this;
    }


    /**
     * Crée la physique de l'objet
     * Ne peut être appelée qu'une seule fois
     * Tout autre appel n'aura aucun effet
     * @return
     */
    public Physics create() {
        if ((!created)&&(defined)&&(fixtured)) {
            CREATION.add(this);
        }
        return this;
    }
    
    public void stepCreate() {
        if (created) { return ; }
        body = world().createBody(definition());
        body.createFixture(fixtures());
        body.setUserData(this.data);
        if (this.data != null) { ((Entity)this.data).onCreate(); }
        created = true ;
    }

    /**
     * Applique une force d'impulsion sur l'objet
     * @param x
     * @param y
     * @return
     */
    public Physics impulse(float x, float y) {
        Vec2 impulse = new Vec2(x, y);
        Vec2 point = body.getWorldCenter();
        body.applyLinearImpulse(impulse, point);
        //body.applyForce(impulse, point);
        return this;
    }

    /**
     * Applique une force de translare
     * @param dvx
     * @param dvy
     * @return
     */
    public Physics translate(int dvx, int dvy) {
        Vec2 v = body.getLinearVelocity();
        float ddvx = (dvx == 0) ? 0 : body.getMass() * (dvx - v.x);
        float ddvy = (dvy == 0) ? 0 : body.getMass() * (dvy - v.y);
        Vec2 iv = new Vec2(ddvx, ddvy);
        impulse(iv.x, iv.y);
        return this;
    }
    
    public Physics move(int dvx, int dvy) {
        body.setLinearVelocity(new Vec2(dvx, dvy));
        return this;
    }
    
    public Physics moveX(int dvx) {
        body.setLinearVelocity(new Vec2(dvx, body.getLinearVelocity().y));
        return this;
    }
    
    public Physics moveY(int dvy) {
        body.setLinearVelocity(new Vec2(body.getLinearVelocity().x, dvy));
        return this;
    }


    /**
     * Angle
     * @return
     */
    public float angle() {
        return created ? body.getAngle() : 0;
    }

    /**
     * X
     * @return
     */
    public float x() {
        return created ? toPixels(body.getPosition().x) : 0;
    }

    /**
     * Y
     * @return
     */
    public float y() {
        return created ? toPixels(body.getPosition().y) : 0;
    }

    /**
     * Initialiseur du monde
     * A utiliser à chaque fois qu'un nouveau monde est à créer
     * Vecteur gravité en paramètre
     * @param gx
     * @param gy
     */
    public static void world(float gx, float gy) {
        if (_world instanceof World) { 
            _world.setContactListener(null); 
            Iterator<Body> it = DESTROYED.iterator();
            while (it.hasNext()) {
                Body b = it.next();
                _world.destroyBody(b);
                it.remove(); 
            }
        }
        Vec2 gravity = new Vec2(gx, gy);
        _world = new World(gravity, true);
        _world.setContactListener(new Collisions());
    }

    /**
     * Physique du monde
     * @return
     */
    public static World world() {
        return _world ;
    }

    /**
     * Met à jour le monde
     * @param step - delta seconde
     * @param vi - Nb itération pour le calcul de la vélocité
     * @param pi - Nb itérations pour le calcul de la position
     */
    public static void update(float step, int vi, int pi) {  
        if (world() == null) { return; }
        world().step(step, vi, pi);
        
        Iterator<Body> it = DESTROYED.iterator();
        while (it.hasNext()) {
            Body b = it.next();
            world().destroyBody(b);
            it.remove(); 
        }
        
        stepCreateAll();
        for (Body b : FORCED) { FORCED.remove(b); 
            Player p = (Player) (b.getUserData());
            b.setTransform(new Vec2(p.getSpawnX(), p.getSpawnY()), b.getAngle()); }
    }

    private static boolean locked = false;
    public static void stepCreateAll() {
        if (locked) { return; }
        locked = true;
        Iterator<Physics> it = CREATION.iterator();
        while (it.hasNext()) {
            Physics p = it.next();
            p.stepCreate(); 
            it.remove();
        }
        locked = false;
    }
    
    /**
     * Raccourci de la méthode update
     */
    public static void update() {
        update(0.01f, 1, 1);
    }

    /**
     * Raccourci de la méthode update
     * @param step
     */
    public static void update(float step) {
        update(step, 6, 2);
    }
    
    public static float toMeters(int pixels) {
        return pixels * 0.02f;
    }
    
    public static int toPixels(float meters) {
        return round(meters * 50.0f);
    }
    
    public void destroy() {
        if (created) { 
            
            DESTROYED.add(body);
            try     {
                body.destroyFixture(body.getFixtureList().getNext());
            } catch (Exception e) { }
            
            created = false ;
        }
    }
    
    public Body getBody() {
        return this.body;
    }
    
    public Physics forcePosition() {
        FORCED.add(body);
        return this;
    }
}
