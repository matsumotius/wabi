package com.myatsumoto.wabi

abstract class Node {
  val count:Int
  val keyString:String
  var parent:Branch = null

  var code = -1
  val SEP  = ","
}
