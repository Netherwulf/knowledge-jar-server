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

        // creating quiz for chapter 2
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
        chapterFour.setName("Tablice");
        chapterFour.setDescription("Rozdział przedstawiający tablice w języku Java");

        // creating quiz for chapter 2
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

        // Quiz 4 Q 2 OQ 3
        OpenQuestion quizFourQuestionTwo = new OpenQuestion();
        quizFour.getOpenQuestions().add(quizFourQuestionTwo);
        quizFourQuestionTwo.setQuiz(quizFour);
        quizFourQuestionTwo.setContent("Który modyfikator dostępu mówi o tym, że do pola klasy mogą mieć dostęp obiekty innych klas?");
        quizFourQuestionTwo.setCorrectAnswer("public");

        // Quiz 4 Q 3 OQ 2
        OpenQuestion quizFourQuestionThree = new OpenQuestion();
        quizFour.getOpenQuestions().add(quizFourQuestionThree);
        quizFourQuestionThree.setQuiz(quizFour);
        quizFourQuestionThree.setContent("Co należy wpisać w miejsce symbolu (###), aby metoda calc() przyjmowała jeden argument typu int?");
        quizFourQuestionThree.setCorrectAnswer("int");
        quizFourQuestionThree.setCodeLink("https://gist.github.com/Netherwulf/12ff7ce4126542d849dc1c1a8912020a.js");

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
        ch4subchapterTwo.setName("Metody");
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

        // ======================================================= Saving Chapter 4 =======================================================
        chapterList.add(chapterFour);

        // saving chapters to database
        chapterRepository.saveAll(chapterList);
        System.out.println("Chapters loaded = " + chapterRepository.count());
        System.out.println("Open Questions loaded = " + openQuestionRepository.count());
        System.out.println("Closed Questions loaded = " + closedQuestionRepository.count());
    }

    private void loadStudents(){
        // initialization of list of chapters
        List<Student> studentList = new ArrayList<Student>();

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
