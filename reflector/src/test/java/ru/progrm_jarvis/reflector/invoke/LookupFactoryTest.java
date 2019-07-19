package ru.progrm_jarvis.reflector.invoke;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

class LookupFactoryTest {

    @Test
    void testInstantiatingFactory() {
        assertThat(LookupFactory.INSTANTIATING_FACTORY.get(), notNullValue());
    }

    @Test
    void testTrustedSingletonFactory() {
        assertThat(LookupFactory.TRUSTED_SINGLETON_FACTORY.get(), notNullValue());
    }
}