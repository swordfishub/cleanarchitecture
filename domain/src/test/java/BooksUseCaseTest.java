import com.mralonso.android.domain.callbacks.BooksCallback;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;
import com.mralonso.android.domain.useCases.BooksUseCase;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BooksUseCaseTest extends TestCase {

    BooksUseCase mBooksUseCase;

    @Mock
    Executor mockExecutor;

    @Mock
    MainThread mockMainThread;

    @Mock
    BooksRepository mockBooksRepository;

    @Mock
    BooksCallback mockBooksCallback;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        mBooksUseCase = Mockito.spy(new BooksUseCase(mockExecutor, mockMainThread));
        mBooksUseCase.setRepository(mockBooksRepository);
        mBooksUseCase.setCallback(mockBooksCallback);
    }

    @Test
    public void testEmptyHouses() {

        ArrayList<Book> booksResponse = new ArrayList();

        when(mockBooksRepository.obtainBooks(any(String.class))).thenReturn(booksResponse);
        mBooksUseCase.run();
        verify(mBooksUseCase).postDataEmptyReceived();
    }

    @Test
    public void testValidHouses() {

        ArrayList<Book> booksResponse = new ArrayList();
        for(int i=0; i<10; i++){
            booksResponse.add(new Book());
        }

        when(mockBooksRepository.obtainBooks(any(String.class))).thenReturn(booksResponse);
        mBooksUseCase.run();
        verify(mBooksUseCase).postDataReceived(booksResponse);
    }

    @Test
    public void testNotValidHouses() {

        when(mockBooksRepository.obtainBooks(any(String.class))).thenReturn(null);
        mBooksUseCase.run();
        verify(mBooksUseCase).postDataNotReceived();
    }
}
