package analyzer;

import fillers.Fill;
import org.openjdk.jmh.annotations.Benchmark;
import org.reflections.Reflections;
import sorters.Sort;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class, which calls reflection-using methods in case
 * of working in runtime, for analyze execution time of
 * different sorters within different array-filler types.
 * @see Analyze
 * @see Sort
 * @see Fill
 */
public class Reflection {
    private static int sortCount;

    public int getSortCount() {
        return this.sortCount;
    }

    /**
     * Finds methods in class {@link Fill} which have
     * annotation {@link fillers.Fill.Filler} and puts their
     * names to the list.
     * @return list of methods names
     */
    public List<String> findMethods(){
        List<String> methodsNames = new ArrayList<>();
        Class<?> clazz = Fill.class;
        Method methods[] = clazz.getMethods();
        for(Method method : methods){
            if (method.isAnnotationPresent(Fill.Filler.class))
                methodsNames.add(method.getName());
        }
        return methodsNames;
    }

    /**
     * Finds classes, which extend {@link Sort} and puts
     * them to the list.
     * @return list of Sort subclasses
     * @see Sort
     */
    public Set<Class<? extends Sort>> findClasses(){
        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends Sort>> subClasses = reflections.getSubTypesOf(Sort.class);
        return subClasses;
    }

    /**
     * Calls sorting methods from arrays, that are filled
     * in different ways using {@link Fill} methods.
     * Counts how long each method works and puts all
     * information about execution time into data list.
     * @param subClasses classes wich extend {@link Sort}
     * @param methodsNames array filler methods
     * @return list of data with methods execution time
     * @throws ReflectiveOperationException
     */
    public List<Object[]> callSortersWithinFillers(Set<Class<? extends Sort>> subClasses, List<String> methodsNames) throws ReflectiveOperationException{
        List<Object[]> data = new ArrayList<>();
        Method methods[] = new Method[methodsNames.size()];

        for(int i = 0; i<methodsNames.size(); i++){
            methods[i] = Fill.class.getMethod(methodsNames.get(i), int.class);
        }

        for (Method method : methods) {
            int[] array = (int[]) method.invoke(Fill.class, 10);
            int i = 0;

            for (Class c : subClasses) {
                if (Modifier.isAbstract(c.getModifiers())) {
                    continue;
                }

                Object sorter = c.getConstructor().newInstance();
                String sortName = sorter.getClass().getSimpleName();
                System.out.println(sortName);
                Method sortMethod = sorter.getClass().getMethod("sort", int[].class);


                sortMethod.invoke(sorter, array);

                long start = System.nanoTime();
                sortMethod.invoke(sorter, array);
                long end = System.nanoTime();

                String fillerName = method.getName();
                data.add(new Object[]{fillerName, sortName, end - start});

                i++;
            }
            sortCount = i;
        }
        return data;
    }
}
