package com.myatsumoto.wabi

import scala.collection.mutable.BitSet

class BitVector(str:String) {

  var vector = new BitSet()
  for (i <- 0 until str.size) {
    if ("1" == str(i).toString) vector += i
  }

  def rank(b:Int, index:Int):Int = {
    var count    = 0
    val expected = (b == 1)
    for (i <- 0 until index) {
      if (expected == vector(i)) count += 1
    }
    return count
  }

  def select(b:Int, limit:Int):Int = {
    var count    = -1
    val expected = (b == 1)
    for (i <- 0 until str.size) {
      if (expected == vector(i)) count += 1
      if (count >= limit) return i
    }
    return -1
  }

}
