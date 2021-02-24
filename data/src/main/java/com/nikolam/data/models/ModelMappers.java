package com.nikolam.data.models;

import com.nikolam.common.models.CommentDomainModel;
import com.nikolam.common.models.PostDomainModel;
import com.nikolam.data.db.models.PostDataModel;

import java.util.ArrayList;

public class ModelMappers {
    public static PostDomainModel postNetworkToDomainModel(PostNetworkModel model) {
        ArrayList<CommentDomainModel> comments = new ArrayList<>();

        for (CommentNetworkModel com : model.getComments()){
            comments.add(commentNetworkToDomainModel(com));
        }

        return new PostDomainModel(model.getAwsImageLink(), model.getComment(), model.getTags(), model.getObjectID(),
                model.getUserID(), model.getAwsThumbnailLink(), model.getDatePostedInMillis(), comments);
    }

    public static PostDomainModel postDataToDomainModel(PostDataModel model) {
        ArrayList<CommentDomainModel> comments = new ArrayList<>();

        for (CommentNetworkModel com : model.getComments()){
            comments.add(commentNetworkToDomainModel(com));
        }

        return new PostDomainModel(model.getAwsImageLink(), model.getComment(), model.getTags(), model.getObjectID(),
                "", model.getAwsThumbnailLink(), model.getDatePostedOnMillis(),comments);
    }

    public static CommentDomainModel commentNetworkToDomainModel(CommentNetworkModel model) {
        return new CommentDomainModel(model.getUserID(), model.getContents(), model.getObjectID());
    }
   // public static CommentDomainModel commentDataToDomainModel(CommentNetworkModel model) {}
}
