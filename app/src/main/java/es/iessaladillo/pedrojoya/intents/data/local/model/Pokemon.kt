package es.iessaladillo.pedrojoya.intents.data.local.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

// TODO: Define las propiedades de un pokemon
@Parcelize
data class Pokemon (val id : Long, val idIcon : Int, val idName : Int, val forsa : Int) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    companion object : Parceler<Pokemon> {

        override fun Pokemon.write(parcel: Parcel, flags: Int) {
            parcel.writeLong(id)
            parcel.writeInt(idIcon)
            parcel.writeInt(idName)
            parcel.writeInt(forsa)
        }

        override fun create(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }
    }
}