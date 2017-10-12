using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bullet : MonoBehaviour {

    public float bulletSpeed;
    public int bulletDamage;
    public float lifetime;
    
    void OnTriggerEnter(Collider collidedGO) {
        if (collidedGO.GetComponent<EnemyManager>() != null) {
            //TODO: Lógica de enemigos
            collidedGO.GetComponent<EnemyManager>().DamageEnemy(bulletDamage);
        }
        if(collidedGO.GetComponent<SpawnEnemy>() != null) {
            Destroy(this.gameObject);
        }
    }
}