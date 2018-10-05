package com.slb.gt.boilerplate.services

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.slb.gt.boilerplate.data.models.Driver
import com.slb.gt.boilerplate.utils.interfaces.SuccessFailCallback
import org.androidannotations.annotations.EBean
import com.google.firebase.firestore.DocumentSnapshot


@EBean(scope = EBean.Scope.Singleton)
open class DatabaseService {
    val driverCollectionPath = "test-driver"
    val pageSize = 10L

    val db = FirebaseFirestore.getInstance()

    open fun createDriver(driver: Driver, callback: SuccessFailCallback<String, Exception>) {
        db.collection(driverCollectionPath)
                .add(driver)
                .addOnSuccessListener {
                    callback.onSuccess(it.id)
                }
                .addOnFailureListener {
                    callback.onFailure(it)
                }
    }

    open fun getDriver(id: String, callback: SuccessFailCallback<Driver, Exception>) {
        db.collection(driverCollectionPath)
                .document(id)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val document = it.getResult()
                        if (document.exists()) {
                            val driver = document.toObject(Driver::class.java)
                            if (driver != null)
                                callback.onSuccess(driver)
                        } else {
                            callback.onFailure(Exception("No such document"))
                        }
                    } else {
                        callback.onFailure(it.exception as Exception)
                    }
                }
    }

    lateinit var driverQuery: Query
    open fun getDrivers(callback: SuccessFailCallback<List<Driver>, Exception>) {
        driverQuery = db.collection(driverCollectionPath)
                .orderBy("firstName")
                .limit(pageSize)
        getMoreDrivers(callback)
    }

    open fun getMoreDrivers(callback: SuccessFailCallback<List<Driver>, Exception>) {
        if (::driverQuery.isInitialized)
            driverQuery.get().addOnSuccessListener {

                if (it.getDocuments().size == 0) {
                    callback.onFailure(Exception("No more documents"))
                    return@addOnSuccessListener
                }

                val lastVisible = it.getDocuments()
                        .get(it.getDocuments().size - 1)
                // Construct a new query starting at this document,
                // get the next 25 cities.
                driverQuery = db.collection(driverCollectionPath)
                        .orderBy("firstName")
                        .startAfter(lastVisible)
                        .limit(pageSize)

                val drivers = arrayListOf<Driver>()

                it.documents.forEach {
                    val driver = it.toObject(Driver::class.java)
                    if (driver != null)
                        drivers.add(driver)
                }

                callback.onSuccess(drivers)

            }.addOnFailureListener {
                callback.onFailure(it)
            }
        else
            getDrivers(callback)
    }
}