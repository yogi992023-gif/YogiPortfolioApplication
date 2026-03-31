package com.yogi.interviewproject.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yogi.interviewproject.data.model.responce.DriverLocation
import javax.inject.Inject


class LocationRepository  @Inject constructor() {

    private val db = FirebaseDatabase.getInstance().reference

    fun listenDriverLocation(
        driverId: String,
        onUpdate: (DriverLocation) -> Unit
    ) {

        db.child("drivers").child(driverId)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val location = snapshot.getValue(DriverLocation::class.java)

                    location?.let { onUpdate(it) }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}