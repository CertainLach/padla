package ru.progrm_jarvis.reflector.wrapper.invoke;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class InvokeDynamicMethodWrapperTest {

    @Test
    void testVoidNoArgs() {
        final class Petya {
            private boolean called;
            private void call() {
                called = true;
            }
        }
        val instance = new Petya();

        assertDoesNotThrow(() -> InvokeDynamicMethodWrapper
                .from(Petya.class.getDeclaredMethod("call"))
                .invoke(instance)
        );
        assertTrue(instance.called);
    }

    @Test
    void testVoid1Arg() {
        val val1 = ThreadLocalRandom.current().nextInt();

        final class Petya {
            private boolean called;
            private void call(final int arg1) {
                assertEquals(val1, arg1);

                called = true;
            }
        }
        val instance = new Petya();

        assertDoesNotThrow(() -> InvokeDynamicMethodWrapper
                .from(Petya.class.getDeclaredMethod("call", int.class))
                .invoke(instance, val1)
        );
        assertTrue(instance.called);
    }

    @Test
    void testVoid2Args() {
        final int val1, val2;
        {
            val random = ThreadLocalRandom.current();
            val1 = random.nextInt();
            val2 = random.nextInt();
        }

        final class Petya {
            private boolean called;
            private void call(final int arg1, final int arg2) {
                assertEquals(val1, arg1);
                assertEquals(val2, arg2);

                called = true;
            }
        }
        val instance = new Petya();

        assertDoesNotThrow(() -> InvokeDynamicMethodWrapper
                .from(Petya.class.getDeclaredMethod("call", int.class, int.class))
                .invoke(instance, val1, val2)
        );
        assertTrue(instance.called);
    }

    @Test
    void testObjectNoArgs() throws NoSuchMethodException {
        val result = "Res0_" + ThreadLocalRandom.current().nextInt();
        final class Petya {
            private boolean called;
            private String call() {
                return result;
            }
        }
        val instance = new Petya();

        assertEquals(result, InvokeDynamicMethodWrapper
                .from(Petya.class.getDeclaredMethod("call"))
                .invoke(instance)
        );
    }

    @Test
    void testObject1Arg() throws NoSuchMethodException {
        final int val1;
        final String result;
        {
            val random = ThreadLocalRandom.current();
            val1 = random.nextInt();
            result = "Res1_" + random.nextInt();
        }

        final class Petya {
            private boolean called;
            private String call(final int arg1) {
                assertEquals(val1, arg1);

                return result;
            }
        }
        val instance = new Petya();

        assertEquals(result, InvokeDynamicMethodWrapper
                .from(Petya.class.getDeclaredMethod("call", int.class))
                .invoke(instance, val1)
        );
    }

    @Test
    void testObject2Args() throws NoSuchMethodException {
        final int val1, val2;
        final String result;
        {
            val random = ThreadLocalRandom.current();
            val1 = random.nextInt();
            val2 = random.nextInt();
            result = "Res2_" + random.nextInt();
        }

        class Petya {
            private String call(final int arg1, final int arg2) {
                assertEquals(val1, arg1);
                assertEquals(val2, arg2);

                return result;
            }
        }
        val instance = new Petya();

        assertEquals(result, InvokeDynamicMethodWrapper
                .from(Petya.class.getDeclaredMethod("call", int.class, int.class))
                .invoke(instance, val1, val2)
        );
    }
}