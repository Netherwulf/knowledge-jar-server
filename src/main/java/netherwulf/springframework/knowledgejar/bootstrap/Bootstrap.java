package netherwulf.springframework.knowledgejar.bootstrap;

import netherwulf.springframework.knowledgejar.models.*;
import netherwulf.springframework.knowledgejar.repositories.ChapterRepository;
import netherwulf.springframework.knowledgejar.repositories.ClosedQuestionRepository;
import netherwulf.springframework.knowledgejar.repositories.OpenQuestionRepository;
import netherwulf.springframework.knowledgejar.repositories.StudentRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ChapterRepository chapterRepository;
    private ClosedQuestionRepository closedQuestionRepository;
    private OpenQuestionRepository openQuestionRepository;
    private StudentRepository studentRepository;

    public Bootstrap(ChapterRepository chapterRepository, ClosedQuestionRepository closedQuestionRepository, OpenQuestionRepository openQuestionRepository, StudentRepository studentRepository) {
        this.chapterRepository = chapterRepository;
        this.closedQuestionRepository = closedQuestionRepository;
        this.openQuestionRepository = openQuestionRepository;
        this.studentRepository = studentRepository;
    }

    private void loadChapters() {
        // initialization of list of chapters
        List<Chapter> chapterList = new ArrayList<Chapter>();

        // ======================================================= creating 1 (first) chapter - Basics of Java =======================================================
        Chapter chapterOne = new Chapter();
        chapterOne.setName("Podstawy Javy");
        chapterOne.setDescription("Rozdział przedstawiający podstawowe informacje o języku Java");

        // creating quiz for chapter 1
        Quiz quizOne = new Quiz();
        quizOne.setName("Quiz 1");
        quizOne.setChapter(chapterOne);
        chapterOne.setQuiz(quizOne);

        // Quiz 1 Q 1 OQ 1
        OpenQuestion quizOneQuestionOne = new OpenQuestion();
        quizOne.getOpenQuestions().add(quizOneQuestionOne);
        quizOneQuestionOne.setQuiz(quizOne);
        quizOneQuestionOne.setContent("Co zostanie wyświetlone na ekranie po wykonaniu poniższego fragmentu kodu?");
        quizOneQuestionOne.setCorrectAnswer("11");
        quizOneQuestionOne.setCodeLink("https://gist.github.com/Netherwulf/a8179340382f9234c967d7b88b6f2280.js");

        // Quiz 1 Q 2 CQ 1
        ClosedQuestion quizOneQuestionTwo = new ClosedQuestion();
        quizOne.getClosedQuestions().add(quizOneQuestionTwo);
        quizOneQuestionTwo.setQuiz(quizOne);
        quizOneQuestionTwo.setContent("W każdym programie napisanym w języku Java: ");

        Statement quizOneQuestionTwoStatementOne = new Statement();
        quizOneQuestionTwo.getStatements().add(quizOneQuestionTwoStatementOne);
        quizOneQuestionTwoStatementOne.setClosedQuestion(quizOneQuestionTwo);
        quizOneQuestionTwoStatementOne.setContent("Muszą być zawsze zadeklarowane dwie zmienne");
        quizOneQuestionTwoStatementOne.setIsCorrect("false");

        Statement quizOneQuestionTwoStatementTwo = new Statement();
        quizOneQuestionTwo.getStatements().add(quizOneQuestionTwoStatementTwo);
        quizOneQuestionTwoStatementTwo.setClosedQuestion(quizOneQuestionTwo);
        quizOneQuestionTwoStatementTwo.setContent("Musi występować metoda main");
        quizOneQuestionTwoStatementTwo.setIsCorrect("true");

        Statement quizOneQuestionTwoStatementThree = new Statement();
        quizOneQuestionTwo.getStatements().add(quizOneQuestionTwoStatementThree);
        quizOneQuestionTwoStatementThree.setClosedQuestion(quizOneQuestionTwo);
        quizOneQuestionTwoStatementThree.setContent("Wszystkie zmienne muszą być typu całkowitoliczbowego");
        quizOneQuestionTwoStatementThree.setIsCorrect("false");

        // Quiz 1 Q 3 OQ 2
        OpenQuestion quizOneQuestionThree = new OpenQuestion();
        quizOne.getOpenQuestions().add(quizOneQuestionThree);
        quizOneQuestionThree.setQuiz(quizOne);
        quizOneQuestionThree.setContent("Co należy wpisać w miejsce oznaczone (###)?");
        quizOneQuestionThree.setCorrectAnswer("System");
        quizOneQuestionThree.setCodeLink("https://gist.github.com/Netherwulf/e741d1049d57768c4272ea9a72473bcf.js");

        // chapter 1 subchapter 1
        Subchapter subchapterOne = new Subchapter();
        chapterOne.getSubchapters().add(subchapterOne);
        subchapterOne.setChapter(chapterOne);
        subchapterOne.setName("Wprowadzenie do Javy");
        subchapterOne.setContent("Java jest językiem programowania wysokiego poziomu, który jest niezależny od platformy." +
                            "\n" +"Oznacza to, że program napisany w Javie może być uruchomiony na wielu urządzeniach.");

        // chapter 1 subchapter 1 question
        ClosedQuestion chapterOneSubchapterOneQuestion = new ClosedQuestion();
        subchapterOne.setClosedQuestion(chapterOneSubchapterOneQuestion);
        chapterOneSubchapterOneQuestion.setSubchapter(subchapterOne);
        chapterOneSubchapterOneQuestion.setContent("Na ilu urządzeniach może być uruchomiony program napisany w Javie?");

        Statement subchapterOneStatementOne = new Statement();
        chapterOneSubchapterOneQuestion.getStatements().add(subchapterOneStatementOne);
        subchapterOneStatementOne.setClosedQuestion(chapterOneSubchapterOneQuestion);
        subchapterOneStatementOne.setContent("Na jednym");
        subchapterOneStatementOne.setIsCorrect("false");

        Statement subchapterOneStatementTwo = new Statement();
        chapterOneSubchapterOneQuestion.getStatements().add(subchapterOneStatementTwo);
        subchapterOneStatementTwo.setClosedQuestion(chapterOneSubchapterOneQuestion);
        subchapterOneStatementTwo.setContent("Na wielu");
        subchapterOneStatementTwo.setIsCorrect("true");

        // chapter 1 subchapter 2
        Subchapter subchapterTwo = new Subchapter();
        chapterOne.getSubchapters().add(subchapterTwo);
        subchapterTwo.setChapter(chapterOne);
        subchapterTwo.setName("Zmienne");
        subchapterTwo.setContent("Zmienne służą do przechowywania danych, które zostaną przetworzone." +
                "\n" +"Podstawowymi typami w Javie są int (całkowitoliczbowy), double (zmiennoprzecinkowy) i String (ciąg znaków)");

        // chapter 1 subchapter 2 question
        OpenQuestion chapterOneSubchapterTwoQuestion = new OpenQuestion();
        subchapterTwo.setOpenQuestion(chapterOneSubchapterTwoQuestion);
        chapterOneSubchapterTwoQuestion.setSubchapter(subchapterTwo);
        chapterOneSubchapterTwoQuestion.setContent("Podaj typ zmiennej, w której można przechować ciąg znaków");
        chapterOneSubchapterTwoQuestion.setCorrectAnswer("String");

        // chapter 1 subchapter 3
        Subchapter subchapterThree = new Subchapter();
        chapterOne.getSubchapters().add(subchapterThree);
        subchapterThree.setChapter(chapterOne);
        subchapterThree.setName("Podstawowe Operatory");
        subchapterThree.setContent("Java oferuje liczne operatory pozwalające na wykonywanie operacji matematycznych." +
                "\n" +"Najbardziej podstawowymi operatorami jest operator dodawania (+), odejmowania (-), mnożenia (*) i dzielenia (/).");
        subchapterThree.setCodeLink("https://gist.github.com/Netherwulf/2770b677dfc352e6fed68f62ec1468d9.js");

        // chapter 1 subchapter 3 question
        OpenQuestion chapterOneSubchapterThreeQuestion = new OpenQuestion();
        subchapterThree.setOpenQuestion(chapterOneSubchapterThreeQuestion);
        chapterOneSubchapterThreeQuestion.setSubchapter(subchapterThree);
        chapterOneSubchapterThreeQuestion.setContent("Podaj operator pozwalający na wykonanie w Javie operacji dzielenia");
        chapterOneSubchapterThreeQuestion.setCorrectAnswer("/");

        // ======================================================= Saving Chapter 1 =======================================================
        chapterList.add(chapterOne);

        // ======================================================= creating 2 (second) chapter - Conditionals and Loops =======================================================
        Chapter chapterTwo = new Chapter();
        chapterTwo.setName("Wyrażenia Warunkowe i Pętle");
        chapterTwo.setDescription("Rozdział przedstawiający wyrażenia warunkowe i pętle w języku Java");

        // creating quiz for chapter 2
        Quiz quizTwo = new Quiz();
        quizTwo.setName("Quiz 2");
        quizTwo.setChapter(chapterTwo);
        chapterTwo.setQuiz(quizTwo);

        // Quiz 2 Q 1 OQ 1
        OpenQuestion quizTwoQuestionOne = new OpenQuestion();
        quizTwo.getOpenQuestions().add(quizTwoQuestionOne);
        quizTwoQuestionOne.setQuiz(quizTwo);
        quizTwoQuestionOne.setContent("Podaj słowo kluczowe rozpoczynające instrukcję warunkową");
        quizTwoQuestionOne.setCorrectAnswer("if");

        // Quiz 2 Q 2 CQ 1
        ClosedQuestion quizTwoQuestionTwo = new ClosedQuestion();
        quizTwo.getClosedQuestions().add(quizTwoQuestionTwo);
        quizTwoQuestionTwo.setQuiz(quizTwo);
        quizTwoQuestionTwo.setContent("Które słowo NIE służy do konstrukcji pętli?");

        Statement quizTwoQuestionTwoStatementOne = new Statement();
        quizTwoQuestionTwo.getStatements().add(quizTwoQuestionTwoStatementOne);
        quizTwoQuestionTwoStatementOne.setClosedQuestion(quizTwoQuestionTwo);
        quizTwoQuestionTwoStatementOne.setContent("while");
        quizTwoQuestionTwoStatementOne.setIsCorrect("false");

        Statement quizTwoQuestionTwoStatementTwo = new Statement();
        quizTwoQuestionTwo.getStatements().add(quizTwoQuestionTwoStatementTwo);
        quizTwoQuestionTwoStatementTwo.setClosedQuestion(quizTwoQuestionTwo);
        quizTwoQuestionTwoStatementTwo.setContent("during");
        quizTwoQuestionTwoStatementTwo.setIsCorrect("true");

        Statement quizTwoQuestionTwoStatementThree = new Statement();
        quizTwoQuestionTwo.getStatements().add(quizTwoQuestionTwoStatementThree);
        quizTwoQuestionTwoStatementThree.setClosedQuestion(quizTwoQuestionTwo);
        quizTwoQuestionTwoStatementThree.setContent("for");
        quizTwoQuestionTwoStatementThree.setIsCorrect("false");

        // Quiz 2 Q 3 OQ 2
        OpenQuestion quizTwoQuestionThree = new OpenQuestion();
        quizTwo.getOpenQuestions().add(quizTwoQuestionThree);
        quizTwoQuestionThree.setQuiz(quizTwo);
        quizTwoQuestionThree.setContent("Co zostanie wyświetlone na ekranie po wykonaniu poniższego fragmentu kodu?");
        quizTwoQuestionThree.setCorrectAnswer("Niepełnoletni");
        quizTwoQuestionThree.setCodeLink("https://gist.github.com/Netherwulf/60a097ebcbc226a089083ab6bf60a4ca.js");

        // chapter 2 subchapter 1
        Subchapter ch2subchapterOne = new Subchapter();
        chapterTwo.getSubchapters().add(ch2subchapterOne);
        ch2subchapterOne.setChapter(chapterTwo);
        ch2subchapterOne.setName("Instrukcja if");
        ch2subchapterOne.setContent("Java zawiera słowo kluczowe if, dzięki któremu można zapisać instrukcję warunkową." +
                "\n" +"Instrukcja wykona się tylko jeśli warunek zapisany w nawiasie przy słowie kluczowym if okaże się prawdziwy ");

        // chapter 2 subchapter 1 question
        ClosedQuestion chapterTwoSubchapterOneQuestion = new ClosedQuestion();
        ch2subchapterOne.setClosedQuestion(chapterTwoSubchapterOneQuestion);
        chapterTwoSubchapterOneQuestion.setSubchapter(ch2subchapterOne);
        chapterTwoSubchapterOneQuestion.setContent("Aby instrukcje zapisane w środku instrukcji warunkowej się wykonały," +
                "\n" + "warunek zapisany w nawiasie przy słowie kluczowym if musi być:");

        Statement ch2subchapterOneStatementOne = new Statement();
        chapterTwoSubchapterOneQuestion.getStatements().add(ch2subchapterOneStatementOne);
        ch2subchapterOneStatementOne.setClosedQuestion(chapterTwoSubchapterOneQuestion);
        ch2subchapterOneStatementOne.setContent("Fałszywy");
        ch2subchapterOneStatementOne.setIsCorrect("false");

        Statement ch2subchapterOneStatementTwo = new Statement();
        chapterTwoSubchapterOneQuestion.getStatements().add(ch2subchapterOneStatementTwo);
        ch2subchapterOneStatementTwo.setClosedQuestion(chapterTwoSubchapterOneQuestion);
        ch2subchapterOneStatementTwo.setContent("Prawdziwy");
        ch2subchapterOneStatementTwo.setIsCorrect("true");

        // chapter 2 subchapter 2
        Subchapter ch2subchapterTwo = new Subchapter();
        chapterTwo.getSubchapters().add(ch2subchapterTwo);
        ch2subchapterTwo.setChapter(chapterTwo);
        ch2subchapterTwo.setName("Pętla for");
        ch2subchapterTwo.setContent("Pętla for jest bardzo użyteczna jeśli chcemy wykonać pewien fragment kodu określoną liczbę razy." +
                "\n" +"Do jej konstrukcji wykorzystujemy słowo kluczowe for");
        ch2subchapterTwo.setCodeLink("https://gist.github.com/Netherwulf/14b1de4852e14585f47e204ae91d874f.js");

        // chapter 2 subchapter 2 question
        OpenQuestion chapterTwoSubchapterTwoQuestion = new OpenQuestion();
        ch2subchapterTwo.setOpenQuestion(chapterTwoSubchapterTwoQuestion);
        chapterTwoSubchapterTwoQuestion.setSubchapter(ch2subchapterTwo);
        chapterTwoSubchapterTwoQuestion.setContent("Podaj słowo kluczowe potrzebne do konstrukcji pętli, która wykona się określoną liczbę razy.");
        chapterTwoSubchapterTwoQuestion.setCorrectAnswer("for");

        // chapter 2 subchapter 3
        Subchapter ch2subchapterThree = new Subchapter();
        chapterTwo.getSubchapters().add(ch2subchapterThree);
        ch2subchapterThree.setChapter(chapterTwo);
        ch2subchapterThree.setName("Pętla while");
        ch2subchapterThree.setContent("Java oferuje również pętlę while, którą możemy skonstruować za pomocą słowa kluczowego while." +
                "\n" +"Instrukcje zawarte wewnątrz tej pętli będą się wykonywać ponownie tylko jeśli warunek określony w nawiasie będzie prawdziwy.");
        ch2subchapterThree.setCodeLink("https://gist.github.com/Netherwulf/51dbbd042c5416ba4a17d703177d44ba.js");

        // chapter 2 subchapter 3 question
        OpenQuestion chapterTwoSubchapterThreeQuestion = new OpenQuestion();
        ch2subchapterThree.setOpenQuestion(chapterTwoSubchapterThreeQuestion);
        chapterTwoSubchapterThreeQuestion.setSubchapter(ch2subchapterThree);
        chapterTwoSubchapterThreeQuestion.setContent("Podaj słowo kluczowe potrzebne do konstrukcji pętli, która będzie się wykonywać do momentu aż jej warunek przestanie być prawdziwy.");
        chapterTwoSubchapterThreeQuestion.setCorrectAnswer("while");

        // ======================================================= Saving Chapter 2 =======================================================
        chapterList.add(chapterTwo);

// ======================================================= creating 3 (third) chapter - Arrays =======================================================
        Chapter chapterThree = new Chapter();
        chapterThree.setName("Tablice");
        chapterThree.setDescription("Rozdział przedstawiający tablice w języku Java");

        // creating quiz for chapter 3
        Quiz quizThree = new Quiz();
        quizThree.setName("Quiz 3");
        quizThree.setChapter(chapterThree);
        chapterThree.setQuiz(quizThree);

        // Quiz 3 Q 1 OQ 1
        OpenQuestion quizThreeQuestionOne = new OpenQuestion();
        quizThree.getOpenQuestions().add(quizThreeQuestionOne);
        quizThreeQuestionOne.setQuiz(quizThree);
        quizThreeQuestionOne.setContent("Co zostanie wyświetlone na ekranie po wykonaniu poniższego fragmentu kodu?");
        quizThreeQuestionOne.setCodeLink("https://gist.github.com/Netherwulf/f6471db6d83607e7b00f116481b844ca.js");
        quizThreeQuestionOne.setCorrectAnswer("2");

        // Quiz 3 Q 2 OQ 3
        OpenQuestion quizThreeQuestionTwo = new OpenQuestion();
        quizThree.getOpenQuestions().add(quizThreeQuestionTwo);
        quizThreeQuestionTwo.setQuiz(quizThree);
        quizThreeQuestionTwo.setContent("Co zostanie wyświetlone na ekranie po wykonaniu poniższego fragmentu kodu?");
        quizThreeQuestionTwo.setCorrectAnswer("17");
        quizThreeQuestionTwo.setCodeLink("https://gist.github.com/Netherwulf/0fa802945ea3fdc1671bd843b77be22e.js");

        // Quiz 3 Q 3 OQ 2
        OpenQuestion quizThreeQuestionThree = new OpenQuestion();
        quizThree.getOpenQuestions().add(quizThreeQuestionThree);
        quizThreeQuestionThree.setQuiz(quizThree);
        quizThreeQuestionThree.setContent("Podaj numer indeksu CZWARTEGO elementu w tablicy");
        quizThreeQuestionThree.setCorrectAnswer("3");

        // chapter 3 subchapter 1
        Subchapter ch3subchapterOne = new Subchapter();
        chapterThree.getSubchapters().add(ch3subchapterOne);
        ch3subchapterOne.setChapter(chapterThree);
        ch3subchapterOne.setName("Tablica");
        ch3subchapterOne.setContent("Tablica to kolekcja zmiennych tego samego typu." +
                "\n" +"Przechowywane w niej zmienne są indeksowane od 0.");

        // chapter 3 subchapter 1 question
        OpenQuestion chapterThreeSubchapterOneQuestion = new OpenQuestion();
        ch3subchapterOne.setOpenQuestion(chapterThreeSubchapterOneQuestion);
        chapterThreeSubchapterOneQuestion.setSubchapter(ch3subchapterOne);
        chapterThreeSubchapterOneQuestion.setContent("Podaj indeks pierwszego elementu w tablicy.");
        chapterThreeSubchapterOneQuestion.setCorrectAnswer("0");

        // chapter 3 subchapter 2
        Subchapter ch3subchapterTwo = new Subchapter();
        chapterThree.getSubchapters().add(ch3subchapterTwo);
        ch3subchapterTwo.setChapter(chapterThree);
        ch3subchapterTwo.setName("Tablice wielowymiarowe");
        ch3subchapterTwo.setContent("Tablice mogą mieć wiele wymiarów. W tablicy dwuwymiarowej najpierw podajemy numer wiersza, a następnie numer kolumny.");
        ch2subchapterTwo.setCodeLink("https://gist.github.com/Netherwulf/42d2943d1a2dc267a7fff34f4d4669b2.js");

        // chapter 3 subchapter 2 question
        OpenQuestion chapterThreeSubchapterTwoQuestion = new OpenQuestion();
        ch3subchapterTwo.setOpenQuestion(chapterThreeSubchapterTwoQuestion);
        chapterThreeSubchapterTwoQuestion.setSubchapter(ch3subchapterTwo);
        chapterThreeSubchapterTwoQuestion.setContent("Podaj co powinno być wpisane w miejsce (###), aby kod wyświetlił zawartość komórki z 4 wiersza i 6 kolumny tabeli.");
        chapterThreeSubchapterTwoQuestion.setCodeLink("https://gist.github.com/Netherwulf/8afc9ed9d13c2359e764b710ed17c217.js");
        chapterThreeSubchapterTwoQuestion.setCorrectAnswer("[3][5]");

        // ======================================================= Saving Chapter 3 =======================================================
        chapterList.add(chapterThree);

        // ======================================================= creating 4 (fourth) chapter - Classes and Objects =======================================================
        Chapter chapterFour = new Chapter();
        chapterFour.setName("Klasy i Obiekty");
        chapterFour.setDescription("Rozdział przedstawiający klasy i obiekty w języku Java");

        // creating quiz for chapter 4
        Quiz quizFour = new Quiz();
        quizFour.setName("Quiz 4");
        quizFour.setChapter(chapterFour);
        chapterFour.setQuiz(quizFour);

        // Quiz 4 Q 1 OQ 1
        OpenQuestion quizFourQuestionOne = new OpenQuestion();
        quizFour.getOpenQuestions().add(quizFourQuestionOne);
        quizFourQuestionOne.setQuiz(quizFour);
        quizFourQuestionOne.setContent("Co należy wpisać w miejsce symbolu (###), aby metoda calc() nie zwracała żadnej wartości?");
        quizFourQuestionOne.setCodeLink("https://gist.github.com/Netherwulf/44f30ca3f278f1416dc13113a284ecf7.js");
        quizFourQuestionOne.setCorrectAnswer("void");

        // Quiz 4 Q 2 OQ 2
        OpenQuestion quizFourQuestionTwo = new OpenQuestion();
        quizFour.getOpenQuestions().add(quizFourQuestionTwo);
        quizFourQuestionTwo.setQuiz(quizFour);
        quizFourQuestionTwo.setContent("Który modyfikator dostępu mówi o tym, że do pola klasy mogą mieć dostęp obiekty innych klas?");
        quizFourQuestionTwo.setCorrectAnswer("public");

        // Quiz 4 Q 3 OQ 3
        OpenQuestion quizFourQuestionThree = new OpenQuestion();
        quizFour.getOpenQuestions().add(quizFourQuestionThree);
        quizFourQuestionThree.setQuiz(quizFour);
        quizFourQuestionThree.setContent("Co należy wpisać w miejsce symbolu (###), aby metoda calc() przyjmowała jeden argument typu int?");
        quizFourQuestionThree.setCorrectAnswer("int");
        quizFourQuestionThree.setCodeLink("https://gist.github.com/Netherwulf/12ff7ce4126542d849dc1c1a8912020a.js");

        // Quiz 4 Q 4 OQ 4
        OpenQuestion quizFourQuestionFour = new OpenQuestion();
        quizFour.getOpenQuestions().add(quizFourQuestionFour);
        quizFourQuestionFour.setQuiz(quizFour);
        quizFourQuestionFour.setContent("Jakiego słowa kluczowego należy użyć, aby uczynić klasę abstrakcyjną?");
        quizFourQuestionFour.setCorrectAnswer("abstract");

        // Quiz 4 Q 5 OQ 5
        OpenQuestion quizFourQuestionFive = new OpenQuestion();
        quizFour.getOpenQuestions().add(quizFourQuestionFive);
        quizFourQuestionFive.setQuiz(quizFour);
        quizFourQuestionFive.setContent("Jakiego słowa kluczowego należy użyć przy tworzeniu interfejsu?");
        quizFourQuestionFive.setCorrectAnswer("interface");

        // chapter 4 subchapter 1
        Subchapter ch4subchapterOne = new Subchapter();
        chapterFour.getSubchapters().add(ch4subchapterOne);
        ch4subchapterOne.setChapter(chapterFour);
        ch4subchapterOne.setName("Klasa");
        ch4subchapterOne.setContent("Klasa opisuje dokładnie to czym będzie obiekt, ale nie jest obiektem." +
                "\n" +"Innymi słowy klasa jest schematem lub opisem, na bazie którego tworzymy obiekty o danych właściwościach.");


        // chapter 4 subchapter 1 question
        OpenQuestion chapterFourSubchapterOneQuestion = new OpenQuestion();
        ch4subchapterOne.setOpenQuestion(chapterFourSubchapterOneQuestion);
        chapterFourSubchapterOneQuestion.setSubchapter(ch4subchapterOne);
        chapterFourSubchapterOneQuestion.setContent("Jak nazywa się schemat lub opis obiektu, wykorzystywany przy jego tworzeniu?");
        chapterFourSubchapterOneQuestion.setCorrectAnswer("klasa");

        // chapter 4 subchapter 2
        Subchapter ch4subchapterTwo = new Subchapter();
        chapterFour.getSubchapters().add(ch4subchapterTwo);
        ch4subchapterTwo.setChapter(chapterFour);
        ch4subchapterTwo.setName("Metoda");
        ch4subchapterTwo.setContent("Metody definiują zachowanie obiektów. Dostęp do metod i pól klasy określają modyfikatory dostępu." +
                "\n" + "Dostęp mogą mieć wszyscy (public), tylko obiekty tej samej klasy (private).");

        // chapter 4 subchapter 2 question
        OpenQuestion chapterFourSubchapterTwoQuestion = new OpenQuestion();
        ch4subchapterTwo.setOpenQuestion(chapterFourSubchapterTwoQuestion);
        chapterFourSubchapterTwoQuestion.setSubchapter(ch4subchapterTwo);
        chapterFourSubchapterTwoQuestion.setContent("Który modyfikator dostępu mówi o tym, że do pola klasy mogą mieć dostęp wyłącznie obiekty tej samej klasy?");
        chapterFourSubchapterTwoQuestion.setCorrectAnswer("private");

        // chapter 4 subchapter 3
        Subchapter ch4subchapterThree = new Subchapter();
        chapterFour.getSubchapters().add(ch4subchapterThree);
        ch4subchapterThree.setChapter(chapterFour);
        ch4subchapterThree.setName("Typ zwracany");
        ch4subchapterThree.setContent("W ciele metody możemy zwrócić wartość za pomocą słowa kluczowego return. Natomiast typ zwracany lub jego brak (void) zaznaczamy przy definicji metody.");
        ch4subchapterThree.setCodeLink("https://gist.github.com/Netherwulf/7c01492e58cdf460e976ff30a5c91baf.js");

        // chapter 4 subchapter 3 question
        OpenQuestion chapterFourSubchapterThreeQuestion = new OpenQuestion();
        ch4subchapterThree.setOpenQuestion(chapterFourSubchapterThreeQuestion);
        chapterFourSubchapterThreeQuestion.setSubchapter(ch4subchapterThree);
        chapterFourSubchapterThreeQuestion.setContent("Jakiego słowa kluczowego należy użyć, aby zwrócić wartość w metodzie?");
        chapterFourSubchapterThreeQuestion.setCorrectAnswer("return");

        // chapter 4 subchapter 4
        Subchapter ch4subchapterFour = new Subchapter();
        chapterFour.getSubchapters().add(ch4subchapterFour);
        ch4subchapterFour.setChapter(chapterFour);
        ch4subchapterFour.setName("Dziedziczenie");
        ch4subchapterFour.setContent("Dziedziczenie umożliwia klasie uzyskać cechy (metody i pola) inne klasy." +
                "\n" + "Dzięki temu dane mogą być bardziej uporządkowane i można nadać im hierarchię podobną do rzeczywistej.");
        ch4subchapterFour.setCodeLink("https://gist.github.com/Netherwulf/6c025e81f5a6331dcd1fd6de72170f30.js");

        // chapter 4 subchapter 4 question
        OpenQuestion chapterFourSubchapterFourQuestion = new OpenQuestion();
        ch4subchapterFour.setOpenQuestion(chapterFourSubchapterFourQuestion);
        chapterFourSubchapterFourQuestion.setSubchapter(ch4subchapterFour);
        chapterFourSubchapterFourQuestion.setContent("Co należy wpisać w miejsce symbolu (###), aby klasa Dog dziedziczyła po klasie Animal?");
        chapterFourSubchapterFourQuestion.setCorrectAnswer("extends");
        chapterFourSubchapterFourQuestion.setCodeLink("https://gist.github.com/Netherwulf/d9f820d458e164910c4df85b7596a33a.js");

        // chapter 4 subchapter 5
        Subchapter ch4subchapterFive = new Subchapter();
        chapterFour.getSubchapters().add(ch4subchapterFive);
        ch4subchapterFive.setChapter(chapterFour);
        ch4subchapterFive.setName("Klasa Abstrakcyjna");
        ch4subchapterFive.setContent("Klasa abstrakcyjna służy do skupienia ogólnych cech (metod i pół) grupy różnych klas." +
                "\n" + "Nie można utworzyć obiektu tej klasy, ale można po niej dziedziczyć." +
                "\n" + "Pozwala to bardziej uporządkować zależności między klasami w programie.");
        ch4subchapterFive.setCodeLink("https://gist.github.com/Netherwulf/249b620836543fe53919593e34f8ba70.js");

        // chapter 4 subchapter 5 question
        ClosedQuestion chapterFourSubchapterFiveQuestion = new ClosedQuestion();
        ch4subchapterFive.setClosedQuestion(chapterFourSubchapterFiveQuestion);
        chapterFourSubchapterFiveQuestion.setSubchapter(ch4subchapterFive);
        chapterFourSubchapterFiveQuestion.setContent("Czy można utworzyć obiekt klasy abstrakcyjnej?");

        Statement ch4subchapterFiveStatementOne = new Statement();
        chapterFourSubchapterFiveQuestion.getStatements().add(ch4subchapterFiveStatementOne);
        ch4subchapterFiveStatementOne.setClosedQuestion(chapterFourSubchapterFiveQuestion);
        ch4subchapterFiveStatementOne.setContent("Tak");
        ch4subchapterFiveStatementOne.setIsCorrect("false");

        Statement ch4subchapterFiveStatementTwo = new Statement();
        chapterFourSubchapterFiveQuestion.getStatements().add(ch4subchapterFiveStatementTwo);
        ch4subchapterFiveStatementTwo.setClosedQuestion(chapterFourSubchapterFiveQuestion);
        ch4subchapterFiveStatementTwo.setContent("Nie");
        ch4subchapterFiveStatementTwo.setIsCorrect("true");

        // chapter 4 subchapter 6
        Subchapter ch4subchapterSix = new Subchapter();
        chapterFour.getSubchapters().add(ch4subchapterSix);
        ch4subchapterSix.setChapter(chapterFour);
        ch4subchapterSix.setName("Interfejs");
        ch4subchapterSix.setContent("Interfejs pozwala skupić ogólne cechy (metody) grupy różnych klas." +
                "\n" + "Nie można utworzyć obiektu interfejsu, ale jedna klasa może implementować wiele interfejsów." +
                "\n" + "Pozwala to zagwarantować obecność pewnych metod w klasach implementujących dany interfejs.");
        ch4subchapterSix.setCodeLink("https://gist.github.com/Netherwulf/7dc789c715a95015cae05242a84a777b.js");

        // chapter 4 subchapter 6 question
        ClosedQuestion chapterFourSubchapterSixQuestion = new ClosedQuestion();
        ch4subchapterSix.setClosedQuestion(chapterFourSubchapterSixQuestion);
        chapterFourSubchapterSixQuestion.setSubchapter(ch4subchapterSix);
        chapterFourSubchapterSixQuestion.setContent("Ile maksymalnie interfejsów może implementować jedna klasa?");

        Statement ch4subchapterSixStatementOne = new Statement();
        chapterFourSubchapterSixQuestion.getStatements().add(ch4subchapterSixStatementOne);
        ch4subchapterSixStatementOne.setClosedQuestion(chapterFourSubchapterSixQuestion);
        ch4subchapterSixStatementOne.setContent("Wiele");
        ch4subchapterSixStatementOne.setIsCorrect("true");

        Statement ch4subchapterSixStatementTwo = new Statement();
        chapterFourSubchapterSixQuestion.getStatements().add(ch4subchapterSixStatementTwo);
        ch4subchapterSixStatementTwo.setClosedQuestion(chapterFourSubchapterSixQuestion);
        ch4subchapterSixStatementTwo.setContent("Jeden");
        ch4subchapterSixStatementTwo.setIsCorrect("false");

        // ======================================================= Saving Chapter 4 =======================================================
        chapterList.add(chapterFour);

        // ======================================================= creating 5 (fifth) chapter - Exceptions =======================================================
        Chapter chapterFive = new Chapter();
        chapterFive.setName("Wyjątki");
        chapterFive.setDescription("Rozdział przedstawiający wyjątki w języku Java");

        // creating quiz for chapter 5
        Quiz quizFive = new Quiz();
        quizFive.setName("Quiz 5");
        quizFive.setChapter(chapterFive);
        chapterFive.setQuiz(quizFive);

        // Quiz 5 Q 1 OQ 1
        OpenQuestion quizFiveQuestionOne = new OpenQuestion();
        quizFive.getOpenQuestions().add(quizFiveQuestionOne);
        quizFiveQuestionOne.setQuiz(quizFive);
        quizFiveQuestionOne.setContent("Co należy wpisać w miejsce symbolu (###), aby zareagować na wszystkie możliwe wyjątki?");
        quizFiveQuestionOne.setCodeLink("https://gist.github.com/Netherwulf/b9e0642afa11629ada784e59a5f0a19c.js");
        quizFiveQuestionOne.setCorrectAnswer("catch");

        // chapter 5 subchapter 1
        Subchapter ch5subchapterOne = new Subchapter();
        chapterFive.getSubchapters().add(ch5subchapterOne);
        ch5subchapterOne.setChapter(chapterFive);
        ch5subchapterOne.setName("Wyjątek");
        ch5subchapterOne.setContent("Wyjątek to problem, który występuje podczas wykonywania programu." +
                "\n" +"Wyjątki często powodują nieplanowanie zakończenie działania programu."+
                "\n" +"Aby się im przeciwstawić, należy obudować fragment kodu mogący wywołać wyjątek blokiem try/catch.");
        ch5subchapterOne.setCodeLink("https://gist.github.com/Netherwulf/2d17f147c58978f757f25b02dd2e7765.js");


        // chapter 5 subchapter 1 question
        ClosedQuestion chapterFiveSubchapterOneQuestion = new ClosedQuestion();
        ch5subchapterOne.setClosedQuestion(chapterFiveSubchapterOneQuestion);
        chapterFiveSubchapterOneQuestion.setSubchapter(ch5subchapterOne);
        chapterFiveSubchapterOneQuestion.setContent("Czy wyjątek może wywołać nieprzewidziane przerwanie pracy programu?");

        Statement ch5subchapterOneStatementOne = new Statement();
        chapterFiveSubchapterOneQuestion.getStatements().add(ch5subchapterOneStatementOne);
        ch5subchapterOneStatementOne.setClosedQuestion(chapterFiveSubchapterOneQuestion);
        ch5subchapterOneStatementOne.setContent("Nie");
        ch5subchapterOneStatementOne.setIsCorrect("false");

        Statement ch5subchapterOneStatementTwo = new Statement();
        chapterFiveSubchapterOneQuestion.getStatements().add(ch5subchapterOneStatementTwo);
        ch5subchapterOneStatementTwo.setClosedQuestion(chapterFiveSubchapterOneQuestion);
        ch5subchapterOneStatementTwo.setContent("Tak");
        ch5subchapterOneStatementTwo.setIsCorrect("true");

        // ======================================================= Saving Chapter 5 =======================================================
        chapterList.add(chapterFive);

        // saving chapters to database
        chapterRepository.saveAll(chapterList);
        System.out.println("Chapters loaded = " + chapterRepository.count());
        System.out.println("Open Questions loaded = " + openQuestionRepository.count());
        System.out.println("Closed Questions loaded = " + closedQuestionRepository.count());
    }

    private void loadStudents(){
        // initialization of list of chapters
        List<Student> studentList = new ArrayList<Student>();

        Student admin = new Student();

        // adding first student admin/admin to student list
        studentList.add(admin);
        admin.setLogin("admin");
        admin.setPassword("admin");
        admin.setName("Jan");
        admin.setSurname("Kowalski");
        admin.setProgress(0);
        admin.setEmail("admin@admin.com");
        admin.setJoinDate("30.10.2018");

        // saving students to database
        studentRepository.saveAll(studentList);
        System.out.println("Students loaded = " + studentRepository.count());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadChapters();
        loadStudents();
    }
}
