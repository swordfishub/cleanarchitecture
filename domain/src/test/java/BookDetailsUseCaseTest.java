import com.mralonso.android.domain.callbacks.BookDetailsCallback;
import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.repositories.BooksRepository;
import com.mralonso.android.domain.useCases.BookDetailsUseCase;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookDetailsUseCaseTest extends TestCase {

    BookDetailsUseCase mBookDetailsUseCase;

    @Mock
    Executor mockExecutor;

    @Mock
    MainThread mockMainThread;

    @Mock
    BooksRepository mockBooksRepository;

    @Mock
    BookDetailsCallback mockBookDetailsCallback;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        mBookDetailsUseCase = Mockito.spy(new BookDetailsUseCase(mockExecutor, mockMainThread));
        mBookDetailsUseCase.setRepository(mockBooksRepository);
        mBookDetailsUseCase.setCallback(mockBookDetailsCallback);
        mBookDetailsUseCase.setId("1111");
    }

    @Test
    public void testHouseDetail() {

        BookDetails bookDetailsResponse = new BookDetails();

        when(mockBooksRepository.obtainBookDetails(any(String.class))).thenReturn(bookDetailsResponse);
        mBookDetailsUseCase.run();
        verify(mBookDetailsUseCase).postDataReceived(bookDetailsResponse);
    }

    @Test
    public void testNotValidHouseDetail() {

        when(mockBooksRepository.obtainBooks(any(String.class))).thenReturn(null);
        mBookDetailsUseCase.run();
        verify(mBookDetailsUseCase).postDataNotReceived();
    }
}
