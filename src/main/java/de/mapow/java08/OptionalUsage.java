package de.mapow.java08;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Optional is not meant to be a mechanism to avoid all types of null pointers. e.g. The mandatory input parameters of
 * methods and constructors will still have to be tested.
 *
 * <p>
 * Like when using null, Optional does not help with conveying the meaning of an absent value. So the caller of the
 * method will still have to check the javadoc of the API to understand the meaning of the absent Optional, in order to
 * deal with it properly.
 * </p>
 *
 * Please note that Optional is not meant to be used in these below contexts, as possibly it won’t buy us anything:
 *
 * <pre>
    - in the domain model layer (it’s not serializable)
    - in DTOs (it’s not serializable)
    - in input parameters of methods
    - in constructor parameters
 * </pre>
 *
 * <blockquote> The JSR-335 EG felt fairly strongly that Optional should not be on any more than needed to support the
 * optional-return idiom only. Someone suggested maybe even renaming it to “OptionalReturn“.</blockquote>
 *
 * @author mapow
 * @since 2024-12-02
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionalUsage {

    public static Optional<Integer> getOptional() {
        // return Optional.of(null) => NullPointerException
        // return null; java:S2789 Methods with an "Optional" return type should never return null.

        return Optional.empty(); // or Optional.ofNullable(null) or e.g. Optional.of(325)
    }
}
