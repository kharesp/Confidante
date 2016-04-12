package edu.vanderbilt.kharesp.messenger

import java.time.format.DateTimeFormatter

/**
  * Created by kharesp on 4/12/16.
  */
class Messenger {
  var listening=true
  val formatter=  DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy")
  val cmdRE=":(.*)".r
  /**
    * Listens to user input and commands in a continuous fashion
    */
  //TODO: Have a callback to trigger new welcome message after 24 hours
  def listen(): Unit = {
    println(java.time.LocalDate.now().format(formatter))
    while(listening) {
      val input= scala.io.StdIn.readLine(">")
      input match {
        case cmdRE(x)  => parse_command(x)
        //TODO: Send data to kafka queue
        case  _ => println("User entered:"+input)
      }
    }
  }
  def print_help(): Unit = println("""Recognized commands are:
                                      :q | :quit to quit
                                      :h | :help to print this help message
                                   """);
  def parse_command(cmd: String): Unit = {
    cmd.toLowerCase match {
      case "q" | "quit" => listening=false
      case "h" | "help" => print_help()
      case  _  => { println(cmd +" is not recognized")
        print_help()
      }
    }
  }

}

object Test extends App {
  new Messenger().listen()
}




