package analyzer;

import sorters.Sort;

import java.util.*;

/**
 * Class which analyzes methods execution time
 * using reflection methods from {@link Reflection}
 */
public class Analyze {
    private Reflection reflection = new Reflection();

    /**
     * Gather all methods and calls them to
     * check execution time.
     * @return list of data
     * @throws ReflectiveOperationException
     */
    public List<Object[]> loadMethods() throws ReflectiveOperationException {
        List<Object[]> data;

        List<String> methodsNames = reflection.findMethods();
        Set<Class<? extends Sort>> subClasses = reflection.findClasses();
        data = reflection.callSortersWithinFillers(subClasses, methodsNames);
        return data;
    }
}
