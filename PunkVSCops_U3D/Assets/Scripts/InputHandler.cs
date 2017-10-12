using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InputHandler : MonoBehaviour {

    private Ray m_Ray;
    private RaycastHit m_RayCastHit;
    private TouchableObject m_CurrentTouchableObject;

	// Update is called once per frame
	void Update () {
		if(Input.touches.Length == 1) {
            Touch touchedFinger = Input.touches[0];
            switch (touchedFinger.phase) {
                case TouchPhase.Ended:
                    m_Ray = Camera.main.ScreenPointToRay(touchedFinger.position);
                    if (Physics.Raycast(m_Ray.origin, m_Ray.direction, out m_RayCastHit, Mathf.Infinity)) {
                        TouchableObject touchableObj = m_RayCastHit.collider.gameObject.GetComponent<TouchableObject>();
                        if (touchableObj) {
                            m_CurrentTouchableObject = touchableObj;
                            //a touchable object selected, so set action in here
                            this.gameObject.GetComponent<PlayerManager>().LookAtSpawn(touchableObj.gameObject);
                        } else { //nothing is selected
                            if (m_CurrentTouchableObject) {
                                m_CurrentTouchableObject = null;
                                //reset
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
	}
}