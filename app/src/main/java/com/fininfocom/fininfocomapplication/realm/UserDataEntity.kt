package com.fininfocom.fininfocomapplication.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

/**
 * Created by Ritik on 2/8/2024.
 */
class UserDataEntity : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var name: String = ""
    var age: Int = 0
    var city: String = ""
}
