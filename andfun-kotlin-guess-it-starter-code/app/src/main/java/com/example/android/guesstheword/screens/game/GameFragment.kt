/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import timber.log.Timber

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        // Get the viewmodel
        Timber.i("Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)


        binding.correctButton.setOnClickListener { viewModel.onCorrect() }
        binding.skipButton.setOnClickListener { viewModel.onSkip() }

        // connect to the Livedata
        viewModel.score.observe(this, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        viewModel.word.observe(this, Observer { newWord ->
            binding.wordText.text = newWord
        })

        viewModel.gameFinished.observe(this, Observer { hasFinished ->
            if(hasFinished) {
                gameFinished()
                viewModel.completeTheGame()
            }
        })

        return binding.root

    }


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(this.activity,"Game Finished!",Toast.LENGTH_SHORT ).show()
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?:0)
        findNavController(this).navigate(action)
    }




    /** Methods for updating the UI **/

    /*private fun updateWordText() {
        binding.wordText.text = viewModel.word.value

    }*/

   /* private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.toString()
    }*/
}
