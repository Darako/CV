//using HoloToolkit.Unity.InputModule;
using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class PlayerManager : MonoBehaviour {

    public GameObject prefabBullet;
    public float rotationSpeed;
    public Slider playerHealthbar;
    private GameObject spawnPositionImLooking;
    int startCounter = 0;
    public int currentPlayerHealth;
    public int shootRate;
    private int maxPlayerHealth;

    private Quaternion targetRotation;

    public void LookAtSpawn(GameObject spawnPosition) {
        spawnPositionImLooking = spawnPosition;           
        Vector3 targetPoint = spawnPositionImLooking.transform.position;
        targetRotation = Quaternion.LookRotation(targetPoint - transform.position, Vector3.up);
    }

    void Start() {
        maxPlayerHealth = currentPlayerHealth;
        Debug.Log(playerHealthbar.maxValue);
        spawnPositionImLooking = this.gameObject.transform.parent.Find("Spawns").Find("Spawn_01").gameObject;
    }

    void Update() {
        startCounter++;
        if(startCounter >= shootRate) {
            startCounter = 0;
            ShootAtDirection();
        }
        transform.rotation = Quaternion.Slerp(transform.rotation, targetRotation, Time.deltaTime * rotationSpeed);
    }

    public void ShootAtDirection() {
        if(spawnPositionImLooking != null) {
            this.transform.Find("Gun").transform.Find("BulletSpawn").transform.LookAt(spawnPositionImLooking.transform);
            GameObject bulletGO = Instantiate(prefabBullet, this.transform.Find("Gun").transform.Find("BulletSpawn").transform) as GameObject;
            bulletGO.transform.parent = this.transform.parent;
            bulletGO.GetComponent<Rigidbody>().velocity = (bulletGO.transform.forward) * bulletGO.GetComponent<Bullet>().bulletSpeed;
        }
    }

    internal void DamagePlayer(int enemyDamage) {
        currentPlayerHealth -= enemyDamage;
        float reducedBar = ((float) enemyDamage) / ((float) maxPlayerHealth);
        playerHealthbar.value -= reducedBar;
        if (currentPlayerHealth <= 0) {
            DestroyPlayer();
        }
    }

    private void DestroyPlayer() {
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
    }
}
