package machine

import com.sourcegraph.semanticdb_javac.Result
import _root_.machine.machine.Resultat

object MachineImpl extends MachineDialogue {
  def ask(s: String): List[String] =
    Resultat.reponseToString(AnalysePhrase.analyser(s))

  // Pour la partie test par le client
  def reinit(): Unit = ???
  def test(l: List[String]): List[String] = {
    l match {
      case head :: next => ask(head) ++ test(next)
      case Nil          => Nil
    }
  }
}
