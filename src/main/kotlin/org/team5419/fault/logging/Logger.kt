package org.team5419.fault.logging

import org.team5419.fault.logging.ReflectingCSVWriter
import java.io.File


class Logger (path: String, headers: Array<String>){

    var txtFile: File

    init{
        textFile = File(path)
        textFile.bufferedWriter().use { out -> out.write("time," + headers.joinToString(",") + "\n")}
    }

    public fun write(){

    }
}
// main logging class
