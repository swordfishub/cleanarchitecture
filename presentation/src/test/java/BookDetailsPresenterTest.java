import com.mralonso.android.domain.data.BookDetails;
import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;
import com.mralonso.android.domain.useCases.BookDetailsUseCase;
import com.mralonso.android.presentation.screens.detail.BookDetailsPresenter;
import com.mralonso.android.presentation.screens.detail.BookDetailsView;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by miguel.rodriguez on 4/5/17.
 */

public class BookDetailsPresenterTest extends TestCase {

    BookDetailsPresenter mBookDetailsPresenter;

    @Mock
    MainThread mockMainThread;

    @Mock
    Executor mockExecutor;

    @Mock
    BookDetailsView mockBookDetailsView;

    @Mock
    BookDetailsUseCase mockUseCase;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        mBookDetailsPresenter = new BookDetailsPresenter(mockUseCase, mockBookDetailsView);
    }

    @Test
    public void testConstructor(){
        assertNotNull(mBookDetailsPresenter);
        assertThat(mBookDetailsPresenter, is(instanceOf(BookDetailsPresenter.class)));
    }

    @Test
    public void testStartPresenting() throws Exception{
        mBookDetailsPresenter.startPresenting();
        verify(mockUseCase).execute();
    }

    @Test
    public void testBack(){
        mBookDetailsPresenter.back();
        verify(mockBookDetailsView).close();
    }

    @Test
    public void testRetry(){
        mBookDetailsPresenter.startPresenting();
        mBookDetailsPresenter.retry();
        verify(mockBookDetailsView).showError(false);
        verify(mockBookDetailsView).showLoading(true);
    }

    @Test
    public void testOnBookDetailsReceived(){
        BookDetails mockBookDetails = new BookDetails();
        mBookDetailsPresenter.onBookDetailsReceived(mockBookDetails);
        verify(mockBookDetailsView).showLoading(false);
        verify(mockBookDetailsView).showBookDetails(mockBookDetails);
    }

    @Test
    public void testOnBookDetailNotReceived(){
        mBookDetailsPresenter.onBooksDetailsNotReceived();
        verify(mockBookDetailsView).showLoading(false);
        verify(mockBookDetailsView).showError(true);
    }
}
