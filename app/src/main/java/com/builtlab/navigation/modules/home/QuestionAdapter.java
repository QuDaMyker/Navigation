package com.builtlab.navigation.modules.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.builtlab.navigation.databinding.QuestionItemBinding;
import com.builtlab.navigation.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Question question);
    }

    private List<Question> questions = new ArrayList<>();
    private OnItemClickListener listener;

    public QuestionAdapter(OnItemClickListener listener) {
        this.listener = listener;
        setHasStableIds(true);
    }

    public void setQuestions(List<Question> questions) {
        if (questions != null) {
            this.questions.clear();
            this.questions.addAll(questions);
            notifyDataSetChanged();
        }
    }

    public void appendList(List<Question> questions) {
        if (questions != null) {
            this.questions.addAll(0, questions);
            notifyItemInserted(0);
        }
    }

    @Override
    public long getItemId(int position) {
        String questionId = questions.get(position).getId();
        return questionId != null ? questionId.hashCode() : RecyclerView.NO_ID;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionItemBinding binding = QuestionItemBinding.inflate(inflater, parent, false);
        return new QuestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.setBinding(question);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        QuestionItemBinding binding;

        public QuestionViewHolder(QuestionItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        void setBinding(Question question) {
            binding.questionText.setText(question.getQuestionText());
            binding.difficultyText.setText(question.getLanguageCode());
            binding.languageCodeText.setText(question.getLanguageCode());
        }
    }
}