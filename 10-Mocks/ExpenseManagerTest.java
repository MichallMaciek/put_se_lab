package put.io.testing.mocks;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import put.io.students.fancylibrary.service.FancyService;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerTest {

    @Test
    public void testCalculateTotal() {
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);

        Expense e1 = new Expense(); e1.setAmount(12);
        Expense e2 = new Expense(); e2.setAmount(13);
        Expense e3 = new Expense(); e3.setAmount(5);

        when(mockRepository.getExpenses()).thenReturn(Arrays.asList(e1, e2, e3));

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);
        assertEquals(30, manager.calculateTotal());
    }

    @Test
    public void testCalculateTotalForCategory() {
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);

        Expense homeEx = new Expense(); homeEx.setAmount(100);
        Expense carEx = new Expense(); carEx.setAmount(200);

        when(mockRepository.getExpensesByCategory(anyString())).thenReturn(Collections.emptyList());
        when(mockRepository.getExpensesByCategory("A")).thenReturn(Arrays.asList(homeEx));
        when(mockRepository.getExpensesByCategory("B")).thenReturn(Arrays.asList(carEx));

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);

        assertEquals(100, manager.calculateTotalForCategory("A"));
        assertEquals(200, manager.calculateTotalForCategory("B"));
        assertEquals(0, manager.calculateTotalForCategory("Food"));
    }

    @Test
    public void testCalculateTotalInDollars() throws ConnectException {
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);

        Expense expense = new Expense(); expense.setAmount(120);
        when(mockRepository.getExpenses()).thenReturn(Arrays.asList(expense));

        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
                invocation -> (Double)invocation.getArgument(0) / 4.0
        );

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);
        assertEquals(30, manager.calculateTotalInDollars());
    }

    @Test
    public void testCalculateTotalInDollarsWithException() throws ConnectException {
        ExpenseRepository mockRepository = mock(ExpenseRepository.class);
        FancyService mockService = mock(FancyService.class);

        Expense expense = new Expense(); expense.setAmount(120);
        when(mockRepository.getExpenses()).thenReturn(Arrays.asList(expense));

        when(mockService.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(new ConnectException());

        ExpenseManager manager = new ExpenseManager(mockRepository, mockService);
        assertEquals(-1, manager.calculateTotalInDollars());
    }
}