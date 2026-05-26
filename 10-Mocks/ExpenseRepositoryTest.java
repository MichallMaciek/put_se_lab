package put.io.testing.mocks;

import static org.mockito.Mockito.*;
import org.mockito.InOrder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import put.io.students.fancylibrary.database.IFancyDatabase;
import java.util.Collections;

public class ExpenseRepositoryTest {

    @Test
    public void testLoadExpenses() {
        MyDatabase myDatabaseMock = new MyDatabase();
        ExpenseRepository repository = new ExpenseRepository(myDatabaseMock);

        repository.loadExpenses();

        assertTrue(repository.getExpenses().isEmpty());
    }

    @Test
    public void testLoadExpensesWithMockito() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        when(mockDatabase.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository repository = new ExpenseRepository(mockDatabase);
        repository.loadExpenses();

        InOrder inOrder = inOrder(mockDatabase);
        inOrder.verify(mockDatabase).connect();
        inOrder.verify(mockDatabase).queryAll();
        inOrder.verify(mockDatabase).close();

        assertTrue(repository.getExpenses().isEmpty());
    }

    @Test
    public void testSaveExpenses() {
        IFancyDatabase mockDatabase = mock(IFancyDatabase.class);
        ExpenseRepository repository = new ExpenseRepository(mockDatabase);

        for (int i = 0; i < 5; i++) {
            repository.addExpense(new Expense());
        }

        repository.saveExpenses();

        verify(mockDatabase, times(5)).persist(any(Expense.class));
    }
}