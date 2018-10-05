package com.slb.gt.bluesilo.data.models

import java.io.Serializable

class Mlt (
        val mlt: Long,
        val truckTrailer: String,
        val driver: String,
        val material: String,
        val customer: String,
        val well: String,
        val bulkPlant: String,
        val createdAt: String,
        val lastUpdatedAt: String,
        val mltStatus: String,
        val silos: Array<Silo>
) : Serializable

enum class MltStatus (val value: String) {
    TractorScanned("TractorScanned"),
    Silo1Scanned("Silo 1 Scanned"),
    Silo2Scanned("Silo 2 Scanned")
}