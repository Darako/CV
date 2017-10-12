using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerUI : MonoBehaviour {

    public Transform anchor;

    void LateUpdate() {
        this.transform.LookAt(Camera.main.transform);
        this.transform.position = anchor.position;
    }
}
    
