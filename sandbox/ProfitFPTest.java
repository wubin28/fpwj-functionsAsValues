import org.junit.*;
import static org.junit.Assert.*;
import java.util.function.IntToDoubleFunction;

public class ProfitFPTest {
    @Test
    public void should_calculate_total_profits_for_the_year_in_FunctionOverTime() {
        // given
        final double[] EXPECTED_SALES_JAN_TO_DEC =
            new double[] { 42.0, 45.6, 43.6, 50.2, 55.6, 54.7,
                    58.0, 57.3, 62.0, 60.3, 71.2, 88.8};
        final FunctionOverTime sales =
                (time) -> EXPECTED_SALES_JAN_TO_DEC[time - 1];

        final FunctionOverTime fixedCosts =
                (time) -> 15.0;

        final FunctionOverTime incrementalCosts =
                (time) -> 5.1 + 0.15 * time;

        final FunctionOverTime profit =
                (time) -> sales.valueAt(time) -
                (fixedCosts.valueAt(time) +
                        incrementalCosts.valueAt(time));

        // when
        Double totalProfits = 0.0;
        for(int time = 1; time <= 12; time ++) {
            totalProfits += profit.valueAt(time);
        }

        // then
        assertEquals(436.4, totalProfits, 0.001);
    }

    @Test
    public void should_calculate_total_profits_for_the_year_in_IntToDoubleFunction() {
        // given
        final double[] EXPECTED_SALES_JAN_TO_DEC =
            new double[] { 42.0, 45.6, 43.6, 50.2, 55.6, 54.7,
                    58.0, 57.3, 62.0, 60.3, 71.2, 88.8};
        final IntToDoubleFunction sales =
                (time) -> EXPECTED_SALES_JAN_TO_DEC[time - 1];

        final IntToDoubleFunction fixedCosts =
                (time) -> 15.0;

        final IntToDoubleFunction incrementalCosts =
                (time) -> 5.1 + 0.15 * time;

        final IntToDoubleFunction profit =
                (time) -> sales.applyAsDouble(time) -
                (fixedCosts.applyAsDouble(time) +
                        incrementalCosts.applyAsDouble(time));

        // when
        Double totalProfits = 0.0;
        for(int time = 1; time <= 12; time ++) {
            totalProfits += profit.applyAsDouble(time);
        }

        // then
        assertEquals(436.4, totalProfits, 0.001);
    }
}