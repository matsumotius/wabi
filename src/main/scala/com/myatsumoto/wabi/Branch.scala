package com.myatsumoto.wabi

case class Branch(left:Node, right:Node, str:String) extends Node {
  val count      = left.count + right.count
  val keyString  = left.keyString + SEP + right.keyString
  val keys       = keyString.split(SEP)
  val leftKeys   = left.keyString.split(SEP)
  val rightKeys  = right.keyString.split(SEP)

  val bitStr     = getBitStr(str)
  val bitVector  = new BitVector(bitStr)

  left.code    = 0
  right.code   = 1
  left.parent  = this
  right.parent = this

  def getBitStr(str:String):String = {
    var res = ""
    for (i <- 0 until str.size) {
      if (isLeft(str(i).toString))
        res += "0"
      if (isRight(str(i).toString))
        res += "1"
    }
    return res
  }

  def isLeft(str:String):Boolean = {
    return leftKeys.exists(_ == str)
  }

  def isRight(str:String):Boolean = {
    return rightKeys.exists(_ == str)
  }
}
