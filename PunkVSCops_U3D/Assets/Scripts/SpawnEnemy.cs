using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Vuforia;

public class SpawnEnemy : MonoBehaviour {

    public Transform endPosition;
    public GameObject prefabEnemy;
    int counterInit = 0;
    int counterLimit;
    TrackableBehaviour.Status trackStatus;
    public Text scoreText;

    void Start() {
        counterLimit = (int)Random.Range(100, 501);
    }

    //Update is called once per frame
    void Update() {
        counterInit++;
        if (counterInit >= counterLimit) {
            counterInit = 0;
            counterLimit = (int)Random.Range(200, 501);
            SpawnAnEnemy();
        }
    }

    public void SpawnAnEnemy() {
        GameObject enemyGO = Instantiate(prefabEnemy, this.transform) as GameObject;
        enemyGO.transform.localPosition = Vector3.zero;
        enemyGO.GetComponent<EnemyManager>().EndPosition = endPosition;
        enemyGO.transform.LookAt(endPosition.transform);
    }
}
