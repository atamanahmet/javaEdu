package com.grade.grade_submition;

// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.grade.grade_submition.repository.GradeRepository;
import com.grade.grade_submition.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    // @Test
    // public void getGradeList() {
    // when(gradeService.getGradeList())
    // .thenReturn(Arrays.asList(
    // new Grade("a", "a", "a"),
    // new Grade("b", "b", "b")));

    // List<Grade> returnedList = gradeService.getGradeList();

    // assertEquals(returnedList.get(0).getName(), "a");

    // }

}
