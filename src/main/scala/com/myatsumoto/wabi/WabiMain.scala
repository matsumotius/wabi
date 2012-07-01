package com.myatsumoto.wabi

object WabiMain {
  def main(args: Array[String]) = {
    val wb = new Wabi(args(0))
    println(wb)
  }
}
