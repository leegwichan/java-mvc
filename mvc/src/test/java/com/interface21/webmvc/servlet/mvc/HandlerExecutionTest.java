package com.interface21.webmvc.servlet.mvc;

import static org.assertj.core.api.Assertions.assertThat;

import com.interface21.webmvc.servlet.ModelAndView;
import com.interface21.webmvc.servlet.view.JspView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HandlerExecutionTest {

    @Test
    void handleTest() throws Exception {
        Object instance = new Counter();
        Method method = Counter.class.getMethod("countUp", HttpServletRequest.class, HttpServletResponse.class);
        HandlerExecution handlerExecution = new HandlerExecution(instance, method);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        handlerExecution.handle(request, response);

        Counter counter = (Counter) instance;
        assertThat(counter.getCount()).isEqualTo(1);
    }


    private static class Counter {

        private int count = 0;

        public ModelAndView countUp(HttpServletRequest request, HttpServletResponse response) {
            count++;
            return new ModelAndView(new JspView("/countUp"));
        }

        public int getCount() {
            return count;
        }
    }
}
