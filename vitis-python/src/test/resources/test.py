import org.vertx.java.platform.impl.JythonVerticleFactory.vertx
import org.vertx.java.platform.impl.JythonVerticleFactory.container
import org.vertx.testtools.VertxAssert

def run_test(test):
  """
  Runs a Python test.
  """
  org.vertx.testtools.VertxAssert.initialize(org.vertx.java.platform.impl.JythonVerticleFactory.vertx)
  method = org.vertx.java.platform.impl.JythonVerticleFactory.container.config().getString('methodName')
  getattr(test, method)()

class TestCase(object):
  """
  A Vitis integration test.
  """
  def assert_true(self, value, message=None):
    """
    Asserts that an expression is true.
    """
    if message is not None:
      org.vertx.testtools.VertxAssert.assertTrue(message, value)
    else:
      org.vertx.testtools.VertxAssert.assertTrue(value)

  def assert_false(self, value, message=None):
    """
    Asserts that an expression is false.
    """
    if message is not None:
      org.vertx.testtools.VertxAssert.assertFalse(message, value)
    else:
      org.vertx.testtools.VertxAssert.assertFalse(value)

  def assert_equals(self, val1, val2, message=None):
    """
    Asserts that two values are equal.
    """
    if message is not None:
      org.vertx.testtools.VertxAssert.assertEquals(message, map_to_java(val1), map_to_java(val2))
    else:
      org.vertx.testtools.VertxAssert.assertEquals(map_to_java(val1), map_to_java(val2))

  def assert_null(self, value, message=None):
    """
    Asserts that a value is null.
    """
    if message is not None:
      org.vertx.testtools.VertxAssert.assertNull(message, value)
    else:
      org.vertx.testtools.VertxAssert.assertNull(value)

  def assert_not_null(self, value, message=None):
    """
    Asserts that a value is not null.
    """
    if message is not None:
      org.vertx.testtools.VertxAssert.assertNotNull(message, value)
    else:
      org.vertx.testtools.VertxAssert.assertNotNull(value)

  def complete(self):
    """
    Completes the test.
    """
    org.vertx.testtools.VertxAssert.testComplete()
