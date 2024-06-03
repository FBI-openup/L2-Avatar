package machine

import java.io.FileInputStream
import opennlp.tools.postag.POSModel
import opennlp.tools.postag.POSTaggerME

trait TraitParseur {

  // Les valeurs
  val ponctuations: List[String]

  val adjectifs: List[String]
  val adverbes: List[String]
  val conjonctions: List[String]
  val determinants: List[String]
  val interjections: List[String]
  val noms: List[String]
  val preposition: List[String]
  val pronoms: List[String]
  val verbes: List[String]

  /** Une fonction qui tronsforme une phrase en une list d'élement de type Mot
    *
    * @param phrase
    *   : une chaine de charatère de type String qui est une phrase francaise
    * @return
    *   une list de mot corespondant à une phrase
    */
  def parser(phrase: String): List[Mot]

  /** Une fonction qui divise une phrase en une list d'élement de type String
    *
    * @param phrase
    * @return
    *   une list de String corespondant à une phrase
    */
  def spliter(phrase: String): List[String]
}

object Parseur extends TraitParseur {
  val ponctuations: List[String] = List(
    ".",
    ",",
    ";",
    ":",
    "?",
    "!",
    "-",
    "—",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "\"",
    "\'",
    "«",
    "»",
    "(",
    ")",
    "/",
    "\\",
    "@",
    "#",
    "$",
    "%",
    "&",
    "*",
    "+",
    "<",
    ">",
    "=",
    "|",
    "~",
    "^",
    "\"",
    "..."
  )

  val adjectifs: List[String] = List()
  val adverbes: List[String] = List()
  val conjonctions: List[String] = List(
    "mais",
    "ou",
    "et",
    "donc",
    "or",
    "ni",
    "car",
    "soit",
    "puisque",
    "quoique",
    "bien que",
    "tant que",
    "après que",
    "avant que",
    "pour que",
    "afin que",
    "de sorte que",
    "de manière que",
    "parce que",
    "comme",
    "lorsque",
    "pendant que",
    "si",
    "quand",
    "ainsi que",
    "à moins que",
    "à condition que",
    "quoique",
    "quel que",
    "quoi que",
    "tel que",
    "lorsque",
    "dès que",
    "aussitôt que"
  )
  val determinants: List[String] = List(
    "le",
    "la",
    "les",
    "l'",
    "l",
    "d",
    "du",
    "des",
    "un",
    "une",
    "des",
    "ce",
    "cet",
    "cette",
    "ces",
    "mon",
    "ma",
    "mes",
    "ton",
    "ta",
    "tes",
    "son",
    "sa",
    "ses",
    "notre",
    "nos",
    "votre",
    "vos",
    "leur",
    "leurs"
  )
  val interjections: List[String] = List(
    "ah",
    "eh",
    "oh",
    "ah bon",
    "bah",
    "hein",
    "ouais",
    "pfft",
    "pouah",
    "youpi",
    "bingo",
    "hourra",
    "bravo",
    "olé",
    "ouch",
    "ouïe",
    "aïe",
    "pfut",
    "zut",
    "flûte",
    "damn",
    "mince",
    "bon sang",
    "sapristi",
    "diantre",
    "cornegidouille",
    "morbleu",
    "sacrebleu"
  )
  val noms: List[String] = List("bonjour", "ville", "hôtel", "mairie")
  val preposition: List[String] = List(
    "à",
    "après",
    "avec",
    "chez",
    "contre",
    "dans",
    "de",
    "depuis",
    "derrière",
    "devant",
    "en",
    "entre",
    "hors",
    "jusqu'à",
    "jusqu à",
    "malgré",
    "nonobstant",
    "outre",
    "par",
    "parmi",
    "pendant",
    "pour",
    "sans",
    "sauf",
    "selon",
    "sous",
    "sur",
    "vers",
    "via"
  )
  val pronoms: List[String] = List(
    "je",
    "tu",
    "il",
    "elle",
    "nous",
    "vous",
    "ils",
    "elles",
    "on",
    "me",
    "te",
    "se",
    "nous",
    "vous",
    "le",
    "la",
    "les",
    "lui",
    "leur",
    "y",
    "en",
    "moi",
    "toi",
    "lui",
    "elle",
    "nous",
    "vous",
    "eux",
    "elles",
    "soi"
  )
  val verbes: List[String] = List()

  def parser(phrase: String): List[Mot] = {
    println(Fichier.getCheminAbsolu("doc/opennlp-fr-ud-ftb-pos-1.0-1.9.3.bin"))
    val modelIn = new FileInputStream(
      Fichier.getCheminAbsolu("doc/opennlp-fr-ud-ftb-pos-1.0-1.9.3.bin")
    )
    val model = new POSModel(modelIn)
    val tagger = new POSTaggerME(model)

    val mots = spliter(phrase)
    val tab_mots = mots.toArray
    val categories = tagger.tag(tab_mots).toList
    getListMot(mots, categories)
  }

  private def getListMot(
      mots: List[String],
      categories: List[String]
  ): List[Mot] = {
    (mots, categories) match {
      case (mot1 :: mot2 :: next_mots, categorie :: next_categories) =>
        getCategories(mot1, mot2, next_mots, categorie, next_categories)
      case (mot :: next_mots, categorie :: next_categories) =>
        getCategories(mot, next_mots, categorie, next_categories)
      case (Nil, Nil) => Nil
      case _          => throw new Exception
    }
  }

  private def getCategories(
      mot: String,
      next_mots: List[String],
      categorie: String,
      next_categories: List[String]
  ): List[Mot] = {
    if (adjectifs.contains(mot))
      Adjectif(mot) :: getListMot(next_mots, next_categories)
    else if (adverbes.contains(mot))
      Adverbe(mot) :: getListMot(next_mots, next_categories)
    else if (conjonctions.contains(mot))
      Conjonction(mot) :: getListMot(next_mots, next_categories)
    else if (determinants.contains(mot))
      Determinant(mot) :: getListMot(next_mots, next_categories)
    else if (interjections.contains(mot))
      Interjection(mot) :: getListMot(next_mots, next_categories)
    else if (noms.contains(mot))
      Nom(mot) :: getListMot(next_mots, next_categories)
    else if (preposition.contains(mot))
      Preposition(mot) :: getListMot(next_mots, next_categories)
    else if (pronoms.contains(mot))
      Pronom(mot) :: getListMot(next_mots, next_categories)
    else if (verbes.contains(mot))
      Verbe(mot) :: getListMot(next_mots, next_categories)
    else
      categorie match {
        case "ADJ" =>
          Adjectif(mot) :: getListMot(next_mots, next_categories)
        case "ADV" => Adverbe(mot) :: getListMot(next_mots, next_categories)
        case "CC" =>
          Conjonction(mot) :: getListMot(next_mots, next_categories)
        case "SCONJ" =>
          Conjonction(mot) :: getListMot(next_mots, next_categories)
        case "CCONJ" =>
          Conjonction(mot) :: getListMot(next_mots, next_categories)
        case "DET" =>
          Determinant(mot) :: getListMot(next_mots, next_categories)
        case "INTJ" =>
          Interjection(mot) :: getListMot(next_mots, next_categories)
        case "NOUN"  => Nom(mot) :: getListMot(next_mots, next_categories)
        case "PROPN" => Nom(mot) :: getListMot(next_mots, next_categories)
        case "NUM"   => Nombre(mot) :: getListMot(next_mots, next_categories)
        case "PART" =>
          Particule(mot) :: getListMot(next_mots, next_categories)
        case "PRP" =>
          Preposition(mot) :: getListMot(next_mots, next_categories)
        case "ADP" =>
          Preposition(mot) :: getListMot(next_mots, next_categories)
        case "PRON" => Pronom(mot) :: getListMot(next_mots, next_categories)
        case "VERB" => Verbe(mot) :: getListMot(next_mots, next_categories)
        case "AUX"  => Verbe(mot) :: getListMot(next_mots, next_categories)
        case _ =>
          Autre(mot) :: getListMot(next_mots, next_categories)
      }
  }

  private def getCategories(
      mot1: String,
      mot2: String,
      next_mots: List[String],
      categorie: String,
      next_categories: List[String]
  ): List[Mot] = {
    val mot: String = mot1 + " " + mot2
    if (adjectifs.contains(mot))
      Adjectif(mot) :: getListMot(next_mots, next_categories)
    else if (adverbes.contains(mot))
      Adverbe(mot) :: getListMot(next_mots, next_categories)
    else if (conjonctions.contains(mot))
      Conjonction(mot) :: getListMot(next_mots, next_categories)
    else if (determinants.contains(mot))
      Determinant(mot) :: getListMot(next_mots, next_categories)
    else if (interjections.contains(mot))
      Interjection(mot) :: getListMot(next_mots, next_categories)
    else if (noms.contains(mot))
      Nom(mot) :: getListMot(next_mots, next_categories)
    else if (preposition.contains(mot))
      Preposition(mot) :: getListMot(next_mots, next_categories)
    else if (pronoms.contains(mot))
      Pronom(mot) :: getListMot(next_mots, next_categories)
    else if (verbes.contains(mot))
      Verbe(mot) :: getListMot(next_mots, next_categories)
    else
      getCategories(mot1, mot2 :: next_mots, categorie, next_categories)
  }

  def spliter(phrase: String): List[String] = {
    phrase.length() match {
      case 0 => List()
      case _ => {
        // Remplacer toutes les occurrences de chiffres (\\d) par une chaîne vide
        val phraseSansChiffre: String = phrase.replaceAll("\\d", "")

        // Remplacer toutes les occurrences de ponctuations par un espace
        val regex = "[${ponctuations.mkString}]"
        val phraseSansPonctuation: String =
          ponctuations.foldLeft(phraseSansChiffre) { (res, p) =>
            res.replace(p, " ")
          }

        // Remplacer plusieurs espaces (\\s+) par un seul
        val phraseSansNEspace: String =
          phraseSansPonctuation.replaceAll("\\s+", " ")

        // Mettre toute la phrase en minuscules
        val phraseMinuscules: String = phraseSansNEspace.toLowerCase()

        // Transformer en une liste
        phraseMinuscules.split(" ").toList.filter(_.length > 0);
      }
    }
  }
}
