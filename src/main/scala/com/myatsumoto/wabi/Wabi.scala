package com.myatsumoto.wabi

import scala.collection.mutable.HashMap

class Wabi(str:String) {
  var queue     = List[Node]()
  var codes     = List[(String, String)]()
  var counts    = new HashMap[String, Int]
  var keys      = new HashMap[String, Leaf]
  var tree:Node = null
  build

  def build = {
    countStr
    makeQueue
    makeTree
  }

  def countStr = {
    for (i <- 0 until str.length) {
      val key   = str(i).toString
      val count = counts.getOrElse(key, 0) + 1
      counts.update(key, count)
    }
  }

  def makeQueue = {
    counts.foreach(arg => {
      var leaf:Leaf = new Leaf(arg._1, arg._2)
      queue = leaf :: queue
      keys.update(arg._1, leaf)
    })
    queue = queue.sortWith((a, b) => a.count < b.count)
  }

  def extractMin:Node = {
    val min = queue.head
    queue   = queue.tail
    return min
  }

  def makeTree = {
    while (queue.size > 1) {
      var branch = new Branch(extractMin, extractMin, str)
      queue = (branch :: queue).sortWith((a, b) => a.count < b.count)
    }
    tree = queue.head
  }

  def rank(l:Int, s:String):Int = {
    var isBranch = true
    var node     = tree
    var count    = l + 1
    var dir      = -1
    while (isBranch) {
      node match {
        case Branch(left, right, str) =>
          val branch = node.asInstanceOf[Branch]
          if (branch.isLeft(s)) {
            node = left
            dir  = 0
          }
          if (branch.isRight(s)) {
            node = right
            dir  = 1
          }
          if (dir == -1) {
            throw new Exception()
          }
          count = math.max(0, branch.bitVector.rank(dir, count))
        case _ =>
          isBranch = false
      }
    }
    return count
  }

  def select(c:Int, s:String):Int = {
    var bitCode = keys(s).code
    var branch  = keys(s).parent
    var count   = c
    while (null != branch) {
      count   = branch.bitVector.select(bitCode, count)
      bitCode = branch.code
      branch  = branch.parent
    }
    return count
  }

}
