package com.example.index

import android.util.Log

fun main(){
    println("hell");

//    var a:Int =10;
//    a=33;
//    println(a);
//    println(a.toString());
//
//
//
//
//    val p:Int =9;
//    println(p);
//
    var name:String ="      salin     ";
    var name1:String ="      sa  lin     ";
    println(name);
    println(name.uppercase());

    var len=name.length;
    println(len);

    var check:Boolean =name.equals("salin");
    println(check);

    println(name.trim());
    println(name1.replace(" ","%20"));
    println(name.plus(name1));

    println("$name");
    println("${name.length}")


}