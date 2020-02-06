package ru.progrm_jarvis.javacommons.classloading;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.progrm_jarvis.javacommons.pair.Pair;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

public interface ClassDefiner {

    /**Uns
     * Defines a class which may be garbage-collected.
     *
     * @param owner class whose permissions will be inherited by the defined one
     * @param bytecode bytecode of the class
     * @return defined class
     */
    Class<?> defineClass(@NonNull MethodHandles.Lookup owner,
                         @Nullable String name, @NonNull byte[] bytecode);

    /**
     * Defines multiple classes which may be garbage-collected.
     *
     * @param owner class whose permissions will be inherited by the defined one
     * @param bytecodes pairs whose first values are canonical class names
     * and the second values are those classes' bytecodes
     * @return defined class in the order their data was passed
     */
    @SuppressWarnings("unchecked")
    Class<?>[] defineClasses(@NonNull MethodHandles.Lookup owner,
                             @NonNull Pair<@Nullable String, @NotNull byte[]>... bytecodes);

    /**
     * Defines multiple classes which may be garbage-collected.
     *
     * @param owner class whose permissions will be inherited by the defined one
     * @param bytecodes bytecodes of the classes
     * @return defined class in the order their data was passed
     */
    Class<?>[] defineClasses(@NonNull MethodHandles.Lookup owner,
                             @NonNull byte[]... bytecodes);

    /**
     * Defines multiple classes which may be garbage-collected.
     *
     * @param owner class whose permissions will be inherited by the defined one
     * @param bytecodes bytecodes of the classes
     * @return defined class in the order their data was passed
     */
    List<Class<?>> defineClasses(@NonNull MethodHandles.Lookup owner,
                                 @NonNull List<@NotNull byte[]> bytecodes);

    /**
     * Defines multiple classes which may be garbage-collected.
     *
     * @param namedBytecode map containing bytecodes by their classes' canonical names
     * @return defined classes by their names
     */
    Map<String, Class<?>> defineClasses(@NonNull MethodHandles.Lookup owner,
                                        @NonNull Map<@Nullable String, @NotNull byte[]> namedBytecode);
}
