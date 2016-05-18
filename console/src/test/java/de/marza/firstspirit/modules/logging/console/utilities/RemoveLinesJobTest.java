package de.marza.firstspirit.modules.logging.console.utilities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.Element;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class RemoveLinesJobTest {

  private final Boolean removeFromStart;
  @Rule
  public MockitoRule rule = MockitoJUnit.rule();
  private RemoveLinesJob testling;
  @Mock
  private DocumentEvent documentEvent;
  @Mock
  private LimitLinesDocumentListener documentListener;
  @Mock
  private Document document;
  @Mock
  private Element rootElement;
  @Mock
  private Element line;

  public RemoveLinesJobTest(final Boolean removeFromStart) {
    this.removeFromStart = removeFromStart;
  }

  @Parameterized.Parameters(name = "removeFromStart={0}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Boolean[]{Boolean.TRUE}, new Boolean[]{Boolean.FALSE});
  }

  @Before
  public void setUp() throws Exception {
    when(documentEvent.getDocument()).thenReturn(document);
    when(document.getDefaultRootElement()).thenReturn(rootElement);
    when(documentListener.getMaximumLines()).thenReturn(100);
    when(documentListener.isRemoveFromStart()).thenReturn(removeFromStart);
    testling = new RemoveLinesJob(documentListener, documentEvent);
  }

  @Test
  public void runDoNothing() throws Exception {
    when(rootElement.getElementCount()).thenReturn(10);
    testling.run();
  }

  @Test
  public void runDoRemoveFormStart() throws Exception {
    assumeTrue(removeFromStart);
    when(rootElement.getElementCount()).thenReturn(101).thenReturn(100);
    when(rootElement.getElement(0)).thenReturn(line);
    testling.run();
    verify(document).remove(0, 0);
  }

  @Test
  public void runDoRemoveFormEnd() throws Exception {
    assumeFalse(removeFromStart);
    when(rootElement.getElementCount()).thenReturn(101).thenReturn(101).thenReturn(100);
    when(rootElement.getElement(anyInt())).thenReturn(line);
    testling.run();
    verify(document).remove(-1, 0);
  }

}