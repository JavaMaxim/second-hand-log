package de.marza.firstspirit.modules.logging.toolbar;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;


public class LoggingViewToolbarItemsPluginTest {

  private LoggingViewToolbarItemsPlugin testling;

  @Before
  public void setUp() throws Exception {
    testling = new LoggingViewToolbarItemsPlugin();
    testling.setUp(null);
  }

  @Test
  public void getItems() throws Exception {
    assertThat(testling.getItems(), is(not(empty())));
    assertThat(testling.getItems(), hasSize(1));
  }

}