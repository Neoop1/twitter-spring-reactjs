package com.gmail.merikbest2015.repository.projection;

import com.gmail.merikbest2015.projection.ListOwnerProjection;
import org.springframework.beans.factory.annotation.Value;

public interface ListUserProjection {
    Long getId();
    String getName();
    String getDescription();
    String getAltWallpaper();
    String getWallpaper();
    boolean getIsPrivate();
    Long getListOwnerId();

    @Value("#{@listsServiceImpl.getListOwnerById(target.listOwnerId)}")
    ListOwnerProjection getListOwner();
}
