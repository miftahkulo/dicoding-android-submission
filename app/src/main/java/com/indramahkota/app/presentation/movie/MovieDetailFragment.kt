package com.indramahkota.app.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.indramahkota.app.databinding.VhDetailsBinding
import com.indramahkota.common.base.BaseBindingFragment
import com.indramahkota.common.utils.Constant
import com.indramahkota.common.utils.dpToPx
import com.indramahkota.domain.model.Movie

class MovieDetailFragment : BaseBindingFragment() {
    private var _binding: VhDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = VhDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUI(view: View, savedInstanceState: Bundle?) {
        val movie: Movie? = args.movie

        with(binding) {
            Glide.with(requireContext())
                .load("${Constant.BASE_IMAGE_URL}/${movie?.posterPath}")
                .transform(RoundedCorners(8.dpToPx(requireContext()).toInt()))
                .into(imgPoster)

            txtTitle.text = movie?.title
            txtRating.text = movie?.voteAverage.toString()
            txtLanguage.text = movie?.originalLanguage
            txtReleaseDate.text = movie?.releaseDate
            txtOverview.text = movie?.overview
        }
    }

    override fun unbindFragment() {
        _binding = null
    }
}