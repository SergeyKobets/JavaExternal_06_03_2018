import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;

import static org.mockito.Mockito.*;

import java.io.*;
import java.net.URL;
import java.net.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class URLControllerTest {
    private URLController urlController;
    private static URLStreamHandlerFactory urlStreamHandlerFactory;
    public static final String HTTP_LOCALHOST = "http://localhost/";
    private String testUrlContent = "<a href=\"https://tmfeed.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">TM Feed</a>\n" +
            "      <a href=\"https://habrahabr.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\" class=\"current\">Хабрахабр</a>\n" +
            "      <a href=\"https://geektimes.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Geektimes</a>\n" +
            "      <a href=\"https://toster.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Тостер</a>\n" +
            "      <a href=\"https://moikrug.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Мой круг</a>\n" +
            "      <a href=\"https://freelansim.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Фрилансим</a>";
    private String expectedUrlTestContent = "<a href=\"https://tmfeed.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">TM Feed</a>      <a href=\"https://habrahabr.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\" class=\"current\">Хабрахабр</a>      <a href=\"https://geektimes.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Geektimes</a>      <a href=\"https://toster.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Тостер</a>      <a href=\"https://moikrug.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Мой круг</a>      <a href=\"https://freelansim.ru?utm_source=tm_habrahabr&utm_medium=tm_top_panel&utm_campaign=tm_promo\">Фрилансим</a>";
    private String urlTestString = "1) <a href=\"https://habrahabr.ru/post/343550/\" class= 2)<a href=\"https://habrahabr.ru/post/3143550/\" class=3)<a href=\"https://habrahabr.ru/post/1343550/\" class=4)<a href=\"https://habrahabr.ru/post/3413550/\" class=5)<a href=\"https://habrahabr.ru/post/3431550/\" class=6)<a href=\"https://habrahabr.ru/post/3435150/\" class=7)<a href=\"https://habrahabr.ru/post/3435510/\" class=8)<a href=\"https://habrahabr.ru/post/3435501/\" class=9)<a href=\"https://habrahabr.ru/post/3435502/\" class=10)<a href=\"https://habrahabr.ru/post/3435520/\" class=11)<a href=\"https://habrahabr.ru/post/3435250/\" class=12)<a href=\"https://habrahabr.ru/post/3432550/\" class=13)<a href=\"https://habrahabr.ru/post/3423550/\" class=14)<a href=\"https://habrahabr.ru/post/3243550/\" class=15)<a href=\"https://habrahabr.ru/post/2343550/\" class=16)<a href=\"https://habrahabr.ru/post/3343550/\" class=17)<a href=\"https://habrahabr.ru/post/33343550/\" class=18)<a href=\"https://habrahabr.ru/post/3433550/\" class=19)<a href=\"https://habrahabr.ru/post/34333550/\" class=20)<a href=\"https://habrahabr.ru/post/3435350/\" class=";
    private String urlWithSpace = "href=\"https://habrahabr .ru/post/343550/";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() {
        urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        URL.setURLStreamHandlerFactory(urlStreamHandlerFactory);

    }

    @Before
    public void initURLController() {
        urlController = new URLController();
    }

    @After
    public void cleanURLController() {
        urlController = null;
    }

    @Test
    public void getContentFromUrl() throws IOException {
        AbstractPublicStreamHandler publicStreamHandler = mock(AbstractPublicStreamHandler.class);
        doReturn(publicStreamHandler).when(urlStreamHandlerFactory).createURLStreamHandler(Matchers.eq("http"));

        URLConnection mockedConnection = mock(URLConnection.class);
        doReturn(mockedConnection).when(publicStreamHandler).openConnection(Matchers.any(URL.class));

        doReturn(new ByteArrayInputStream(testUrlContent.getBytes("UTF-8"))).when(mockedConnection).getInputStream();

        String result = URLController.getContentFromUrl(HTTP_LOCALHOST);
        assertEquals(expectedUrlTestContent, result);
    }

    @Test
    public void selectUrlFromContentTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/Users/admin/My_Projects/JavaExternal_06_03_2018/Lesson8_SearchUrl/src/main/resources/Java — Википедия.html"));
        String content = null;
        StringBuilder sb = new StringBuilder();
        while ((content = reader.readLine()) != null) {
            sb.append(content);
        }
        content = sb.toString();

        String[] urls = {"http://java.sun.com/javase/6/docs/api/java/lang/Long.html",
                "https://ru.wikipedia.org/wiki/Java",
                "https://en.wikipedia.org/wiki/Nominative_type_system",
                "https://upload.wikimedia.org/wikipedia/ru/thumb/3/39/Java_logo.svg/1200px-Java_logo.svg.png",
                "https://wikimedia.org/api/rest_v1/media/math/render/svg/bddbb0e4420a7e744cf71bd71216e11b0bf88831",
                "https://en.wikipedia.org/wiki/Java_Data_Objects",
                "http://java.sun.com/javase/6/docs/api/java/lang/Void.html",
                "https://wikimedia.org/api/rest_v1/media/math/render/svg/ca2608c4b5fd3bffc73585f8c67e379b4e99b6f1",
                "http://www.w3.org/1998/Math/MathML",
                "http://java.sun.com/javase/6/docs/api/java/lang/Boolean.html",
                "http://java.sun.com/javase/6/docs/api/java/lang/Integer.html",
                "https://wikimedia.org/api/rest_v1/media/math/render/svg/c26c105004f30c27aa7c2a9c601550a4183b1f21",
                "https://en.wikipedia.org/wiki/Generic_Java",
                "http://java.sun.com/javase/6/docs/api/java/lang/Character.html",
                "https://en.wikipedia.org/wiki/COIN-OR",
                "https://en.wikipedia.org/wiki/Pizza_(programming_language)",
                "http://java.sun.com/javase/6/docs/api/java/lang/Short.html",
                "http://java.sun.com/javase/6/docs/api/java/lang/Byte.html",
                "https://en.wikipedia.org/wiki/Mesa_(programming_language)",
                "http://java.com"
        };

        Set<String> set = URLController.selectUrlFromContent(content);
        String[] actual = set.toArray(new String[set.size()]);

        assertTrue(Arrays.equals(actual, urls));


    }

    @Test
    public void getWordsFromUrl() throws IOException {
        thrown.expect(IOException.class);
        Set<String> set = new HashSet<>();
        set.add("s");
        URLController.getWordsFromUrl(set, "java");

    }

    @Test
    public void getContentFromUrlException() throws IOException {
        thrown.expect(IOException.class);
        URLController.getContentFromUrl("");
    }


    abstract public class AbstractPublicStreamHandler extends URLStreamHandler {
        @Override
        public URLConnection openConnection(URL url) throws IOException {
            return null;
        }
    }
}