import com.mralonso.android.data.utils.DeviceNetworkManager;
import com.mralonso.android.domain.data.Book;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.useCases.BooksUseCase;
import com.mralonso.android.presentation.screens.books.BooksPresenter;
import com.mralonso.android.presentation.screens.books.BooksView;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.verify;

public class BooksPresenterTest extends TestCase {

    BooksPresenter mBooksPresenter;

    @Mock
    MainThread mockMainThread;

    @Mock
    Executor mockExecutor;

    @Mock
    BooksView mockBooksView;

    @Mock
    BooksUseCase mockUseCase;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        mBooksPresenter = new BooksPresenter(mockUseCase, mockBooksView);
    }

    @Test
    public void testConstructor(){
        assertNotNull(mBooksPresenter);
        assertThat(mBooksPresenter, is(instanceOf(BooksPresenter.class)));
    }

    @Test
    public void testStartPresenting() throws Exception{
        mBooksPresenter.startPresenting();
        verify(mockUseCase).execute();
    }

    @Test
    public void testBack(){
        mBooksPresenter.back();
        verify(mockBooksView).close();
    }

    @Test
    public void testRetry(){
        mBooksPresenter.startPresenting();
        mBooksPresenter.retry();
        verify(mockBooksView).showError(false);
        verify(mockBooksView).showLoading(true);
    }

    @Test
    public void testBookSelected(){
        Book mockBook = new Book();
        mBooksPresenter.bookSelected(mockBook);
        verify(mockBooksView).showBookDetail(mockBook);
    }

    @Test
    public void testOnBookReceived(){
        ArrayList<Book> mockBooks = new ArrayList<>();
        mBooksPresenter.onBooksReceived(mockBooks);
        verify(mockBooksView).showLoading(false);
        verify(mockBooksView).showBooks(mockBooks);
    }

    @Test
    public void testOnBooksNotReceived(){
        mBooksPresenter.onBooksNotReceived();
        verify(mockBooksView).showLoading(false);
        verify(mockBooksView).showError(true);
    }
}
