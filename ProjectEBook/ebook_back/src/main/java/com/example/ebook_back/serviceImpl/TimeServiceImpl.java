package com.example.ebook_back.serviceImpl;
import com.example.ebook_back.service.TimeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("session")
public class TimeServiceImpl implements TimeService{
    private Long startTime;
    @Override
    public Long TimeCount(boolean isStart){
        if(isStart){
            startTime = System.currentTimeMillis();
            return 0L;
        }
        else{
            Long TimeCount = System.currentTimeMillis() - startTime;
            startTime = 0L;
            return TimeCount;
        }
    }
}
