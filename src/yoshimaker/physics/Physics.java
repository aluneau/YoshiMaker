/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yoshimaker.physics;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

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
    
    /**
     * Contructeur
     * Note le constructeur ne fait qu'initaliser les données
     * Celles-ci sont à initaliser manuellement
     * Exemple de code :
     * 
     * //Initialisation
     * Physics p = new Physics();
     * p.define(BodyType.STATIC).at(0, 0).hitbox(1, 1).fixtures(0.5f, 0.3.f, 0.5f).create();
     * 
     * //Mis à jour du monde
     * Physics.render(delta);
     * 
     * //Mise à jour de l'entité liée
     * entity.x = p.x();
     * entity.y = p.y();
     * entity.angle = p.angle();
     * 
     */
    Physics() {
        //Initialisation
        created = false ;
        defined = false ;
        hitboxed = false ;
        fixtured = false ;
        //JBox 2D initialisation
        _definition = new BodyDef();
        _hitbox = new PolygonShape();
        _fixtures = new FixtureDef(); 
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
    public Physics definition(BodyType type) {
        if (!created) {
            _definition.type = type;
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
        return definition(type);
    }
    
    /**
     * Position du corps de l'objet
     * @return 
     */
    public Vec2 at() {
        return body.getPosition();
    }
    
    /**
     * Met à jour la position du corps de l'objet
     * @param x
     * @param y
     * @return 
     */
    public Physics at(float x, float y) {
        body.getPosition().set(x, y);
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
    public Physics hitbox(float hx, float hy) {
        if (!created) {
            _hitbox.setAsBox(hx, hy);
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
            body = world().createBody(definition());
            body.createFixture(fixtures());
            created = true ;
        }
        return this;
    } 
    
    /**
     * En cours :D
     *//*
    public Physics() {
        Vec2 impulse = new Vec2(0, 50f);
        Vec2 point = body.getWorldPoint(body.getWorldCenter());
        body.applyLinearImpulse(impulse, point);
        body.
        return this;
    }
    */
    
    /**
     * Angle
     * @return 
     */
    public float angle() {
        return body.getAngle();
    }
    
    /**
     * X
     * @return 
     */
    public float x() {
        return body.getPosition().x;
    }
    
    /**
     * Y
     * @return 
     */
    public float y() {
        return body.getPosition().y;
    }
    
    /**
     * Initialiseur du monde
     * A utiliser à chaque fois qu'un nouveau monde est à créer
     * Vecteur gravité en paramètre
     * @param gx
     * @param gy 
     */
    public static void world(float gx, float gy) {
        Vec2 gravity = new Vec2(gx, gy);
        _world = new World(gravity, true);
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
        world().step(step, vi, pi);
    }
    
    /**
     * Raccourci de la méthode update
     */
    public static void update() {
        update(1.0f/60.0f, 6, 2);
    }
    
    /**
     * Raccourci de la méthode update
     * @param step
     */
    public static void update(float step) {
        update(step, 6, 2);
    }
}
