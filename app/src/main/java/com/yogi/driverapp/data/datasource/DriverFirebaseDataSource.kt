package com.yogi.driverapp.data.datasource

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yogi.driverapp.data.model.responce.DriverLocation
import com.yogi.driverapp.domain.model.CommonLocation
import javax.inject.Inject

class DriverFirebaseDataSource @Inject constructor() {

    private val db = FirebaseDatabase.getInstance().reference

    // DRIVER SEND
    fun sendDriverLocation(driverId: String, location: CommonLocation) {
        db.child("drivers").child(driverId).setValue(location)
    }

    // DRIVER LISTEN USER
    fun listenUserLocation(
        userId: String,
        onUpdate: (CommonLocation) -> Unit
    ) {
        db.child("users")
            .child(userId)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.getValue(CommonLocation::class.java)
                    data?.let { onUpdate(it) }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }
}