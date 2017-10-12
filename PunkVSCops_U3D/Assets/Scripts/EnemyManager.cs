using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class EnemyManager : MonoBehaviour {


    private Transform startPosition;
    public Transform StartPosition {
        get { return startPosition; }
        set { startPosition = value; }
    }
    private Transform endPosition;
    public Transform EndPosition {
        get { return endPosition; }
        set { endPosition = value; }
    }

    public Slider enemyHealthbar;
    public Text score;

    public float enemySpeed;
    private float startTime;
    private float journeyLength;

    public int currentEnemyHealth;
    private int maxEnemyHealth;
    public int enemyDamage;
    
    void Start() {
        maxEnemyHealth = currentEnemyHealth;
        startPosition = this.transform.parent.transform;
        startTime = Time.time;
        journeyLength = Vector3.Distance(startPosition.position, endPosition.position);
    }

    void OnTriggerEnter(Collider player) {
        if (player != null)
            if (player.GetComponent<PlayerManager>()) {
                player.GetComponent<PlayerManager>().DamagePlayer(enemyDamage);
                Destroy(this.gameObject);
            }
    }

    public void DamageEnemy(int bulletDamage) {
        currentEnemyHealth -= bulletDamage;
        float reducedBar = ((float)bulletDamage) / ((float)maxEnemyHealth);
        enemyHealthbar.value -= reducedBar;
        if (currentEnemyHealth <= 0) {
            KillEnemy();
        }
    }

    void Update() {
        if(endPosition != null && startPosition != null) {
            float distCovered = (Time.time - startTime) * enemySpeed;
            float fracJourney = distCovered / journeyLength;
            transform.position = Vector3.Lerp(startPosition.position, endPosition.position, fracJourney);
        }
    }

    public void KillEnemy() {
        //TODO: Sumar puntos
        //Dead Cops: 0
        string titleScore = score.text.Split(':')[0];
        int currentScore = int.Parse(score.text.Split(':')[1]);
        score.text = titleScore + (currentScore + 1);
        Destroy(this.gameObject);
    }

}
