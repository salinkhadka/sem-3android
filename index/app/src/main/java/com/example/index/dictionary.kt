package com.example.index

fun main(){
    var data= mapOf(
        "nepal" to "hello",
        "india" to "hey",
        "china" to "hk",


    )
    println("ENter a name")
    var inp:String= readln()

    println(data[inp].toString())
}