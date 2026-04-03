package com.yogi.interviewproject.data.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yogi.interviewproject.data.model.responce.CommonLocation
import com.yogi.interviewproject.data.model.responce.DriverLocation
import javax.inject.Inject

class FirebaseDataSourceTracking @Inject constructor()  {

    private val db = FirebaseDatabase.getInstance().reference

    fun sendUserLocation(userId: String, location: CommonLocation) {
        db.child("users")
            .child(userId)
            .setValue(location)
    }

    fun listenDriverLocation(
        driverId: String,
        onUpdate: (CommonLocation) -> Unit
    ) {

        db.child("drivers")
            .child(driverId)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val data = snapshot.getValue(CommonLocation::class.java)

                    data?.let { onUpdate(it) }
                }

                override fun onCancelled(error: DatabaseError) {
                    error.message
                }
            })
    }
}