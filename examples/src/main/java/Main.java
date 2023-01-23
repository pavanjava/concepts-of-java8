import com.sparkdev.functions.Functions;
import com.sparkdev.model.Customer;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        /*Consumer<String> loggerConsumer = new Functions().getLoggerConsumer();
        int a = 10;
        int b = 0;

        try {
            int result = a/b;
        }catch (Exception exception){
            loggerConsumer.accept(exception.getMessage());
        }*/

       /* Function<List<Integer>, List<Integer>> oddsFunction = new Functions().getOddsFunction();
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> output = oddsFunction.apply(input);
        System.out.println(output);

        Supplier<List<Integer>> listSupplier = new Functions().getRandomIntListGenerator();
        System.out.println(listSupplier.get());

        Supplier<List<Customer>> customerSupplier = new Functions().getCustomerSupplier();
        List<Customer> customers = customerSupplier.get();

        List<String> emails = customers.stream().map(Customer::getEmail).toList().stream().map(String::toUpperCase).toList();
        System.out.println(emails);

        List<List<String>> phoneNumbers =  customers.stream().map(Customer::getPhoneNumbers).toList();
        List<String> phoneNos =  customers.stream().flatMap(customer -> customer.getPhoneNumbers().stream()).toList();

        System.out.println(phoneNumbers);
        System.out.println(phoneNos);

        List<Integer> randomIntegers = listSupplier.get();
        randomIntegers = randomIntegers.stream().sorted().toList();
        System.out.println(randomIntegers);

        List<Integer> randomInts = listSupplier.get();
        int sum = randomInts.stream().reduce(0, Integer::sum);
        System.out.println(sum);*/

        String str = "this tutorial will guide you to understand java 8 stream api  map-reduce concept with Realtime implementation, all uploaded content in this channel is mine and its not copied from any community , you are free to use source code from above mentioned GitHub account";

        List<String> wordList = Arrays.stream(str.split(" ")).toList();
        Map<String, Long> result = wordList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);

        result = result.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> {
            throw new IllegalStateException();
        }, LinkedHashMap::new));
        System.out.println(result);
    }
}