package de.marza.firstspirit.modules.logging.console.utilities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;

import javax.swing.JEditorPane;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReadTextFromFileTest {

  public static final String EOL = System.lineSeparator();
  @Rule
  public SystemErrRule rule = new SystemErrRule();

  private ReadTextFromFile testling;

  @Before
  public void setUp() throws Exception {
    testling = new ReadTextFromFile("/test.txt");
  }

  @Test
  public void readAsJEditor() throws Exception {
    final JEditorPane editorPane = testling.readAsJEditor();

    final String expectedText = "<html>" + EOL + "  " +
        "<head>" + EOL + "    " + EOL + "  </head>" + EOL + "  " +
        "<body>" + EOL + "    This is a test!" + EOL + "  </body>" + EOL +
        "</html>" + EOL;
    assertThat(editorPane.getText(), is(expectedText));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArgumentConstructor() throws Exception {
    new ReadTextFromFile(null);
  }

  @Test
  public void testFileNotFound() throws Exception {

    rule.enableLog();

    testling = new ReadTextFromFile("/test.text");

    testling.readAsJEditor();

    assertThat("An error occurred while reading the about text: java.lang.NullPointerException" + EOL,
        is(rule.getLog()));
  }
}